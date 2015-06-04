package io.github.yzernik.bitcoinscodec.structures

import scala.language.existentials
import scala.language.implicitConversions

import io.github.yzernik.bitcoinscodec.messages._
import io.github.yzernik.bitcoinscodec.util.Util

import scodec.Attempt.{ Failure, Successful }
import scodec.Codec
import scodec.bits.BitVector
import scodec.bits.ByteVector
import scodec.codecs.bytes
import scodec.codecs.uint32L

trait Message { self =>
  type E >: self.type <: Message
  def companion: MessageCompanion[E]
  def instance: E = self
}

trait MessageCompanion[E <: Message] {
  def codec(version: Int): Codec[E]
  def command: String
}

object Message {

  def padCommand(command: String) = {
    ByteVector(command.getBytes()) ++
      ByteVector.fill(12 - command.length())(0)
  }

  def decodeHeader(bits: BitVector, magic: Long, version: Int) = {
    for {
      m <- uint32L.decode(bits).flatMap { mg =>
        if (mg.value == magic)
          Successful(mg)
        else
          Failure(scodec.Err("magic did not match."))
      }
      c <- bytes(12).decode(m.remainder)
      command = c.value
      l <- uint32L.decode(c.remainder)
      length = l.value
      ch <- uint32L.decode(l.remainder)
      chksum = ch.value
      (payload, rest) = ch.remainder.splitAt(length * 8)
    } yield (command, length, chksum, payload, rest)
  }

  def decodePayload(payload: BitVector, version: Int, chksum: Long, command: ByteVector) = {
    val cmd = MessageCompanion.byCommand(command)
    cmd.codec(version).decode(payload).flatMap { p =>
      if (!p.remainder.isEmpty)
        Failure(scodec.Err("payload length did not match."))
      else if (Util.checksum(payload.toByteVector) == chksum) {
        Successful(p)
      } else {
        Failure(scodec.Err("checksum did not match."))
      }
    }
  }

  def codec(magic: Long, version: Int): Codec[Message] = {

    def encode(msg: Message) = {
      val c = msg.companion.codec(version)
      for {
        magic <- uint32L.encode(magic)
        command <- bytes(12).encode(padCommand(msg.companion.command))
        payload <- c.encode(msg)
        length <- uint32L.encode(payload.length / 8)
        chksum <- uint32L.encode(Util.checksum(payload.toByteVector))
      } yield magic ++ command ++ length ++ chksum ++ payload
    }

    def decode(bits: BitVector) = {
      for {
        metadata <- decodeHeader(bits, magic, version)
        (command, length, chksum, payload, rest) = metadata
        msg <- decodePayload(payload, version, chksum, command)
      } yield msg
    }

    Codec[Message](encode _, decode _)
  }

}

object MessageCompanion {
  val all: Set[MessageCompanion[_ <: Message]] = Set(Addr, Alert, Block, GetAddr, GetBlocks,
    GetData, GetHeaders, Headers, Inv, MemPool, NotFound, Ping, Pong, Reject,
    Tx, Verack, Version)
  val byCommand: Map[ByteVector, MessageCompanion[_ <: Message]] = {
    require(all.map(_.command).size == all.size, "Type headers must be unique.")
    all.map { companion => Message.padCommand(companion.command) -> companion }.toMap
  }
}

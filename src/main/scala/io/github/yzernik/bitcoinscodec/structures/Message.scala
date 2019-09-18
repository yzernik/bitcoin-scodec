package io.github.yzernik.bitcoinscodec.structures

import io.github.yzernik.bitcoinscodec.messages._
import io.github.yzernik.bitcoinscodec.util.Util
import scodec.Attempt.{Failure, Successful}
import scodec.bits.{BitVector, ByteVector}
import scodec.codecs.{bytes, uint32L}
import scodec.{Codec, DecodeResult}

import scala.language.{existentials, implicitConversions}

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

  def padCommand(command: String) =
    ByteVector(command.getBytes()) ++
      ByteVector.fill(12 - command.length())(0)

  private def decodeHeader(bits: BitVector, magic: Long, version: Int) =
    for {
      m <- uint32L.decode(bits).flatMap { mg =>
        if (magic != mg.value)
          Failure(scodec.Err("magic did not match."))
        else
          Successful(mg)
      }
      c <- bytes(12).decode(m.remainder)
      command = c.value
      l <- uint32L.decode(c.remainder)
      length = l.value
      ch <- uint32L.decode(l.remainder).flatMap{ p =>
        if (p.remainder.length < length * 8)
          Failure(scodec.Err("payload is less than specified length."))
        else
          Successful(p)
      }
      chksum = ch.value
      (payload, rest) = ch.remainder.splitAt(length * 8)
    } yield (command, length, chksum, payload, rest)

  private def decodePayload(payload: BitVector, length: Long, version: Int, chksum: Long, command: ByteVector) = {
    val cmd = MessageCompanion.byCommand(command)
    val payloadCodec = cmd.codec(version)
    payloadCodec.decode(payload).flatMap { p =>
      if (chksum != Util.checksum(payload.toByteVector))
        Failure(scodec.Err("checksum did not match."))
      else
        Successful(p)
    }
  }

  def codec(magic: Long, version: Int): Codec[Message] = {

    def encode(msg: Message) = {
      val payloadCodec = msg.companion.codec(version)
      for {
        magic <- uint32L.encode(magic)
        command <- bytes(12).encode(padCommand(msg.companion.command))
        payload <- payloadCodec.encode(msg)
        length <- uint32L.encode(payload.length / 8)
        chksum <- uint32L.encode(Util.checksum(payload.toByteVector))
      } yield magic ++ command ++ length ++ chksum ++ payload
    }

    def decode(bits: BitVector) =
      for {
        metadata <- decodeHeader(bits, magic, version)
        (command, length, chksum, payload, rest) = metadata
        msg <- decodePayload(payload, length, version, chksum, command)
      } yield DecodeResult(msg.value, rest)

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

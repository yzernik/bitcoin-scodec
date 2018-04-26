package lktk.bmsg.structures

import lktk.bmsg.messages._
import lktk.bmsg.util.Util

import scala.language.existentials
import scala.language.implicitConversions

import scodec.Attempt.{Failure, Successful}
import scodec.{Attempt, Codec, DecodeResult}
import scodec.bits.BitVector
import scodec.codecs.uint32L
import scodec.codecs._

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
  def commandCodec = fixedSizeBytes(12, ascii)

  def magicCodec(magic: Long): Codec[Long] =
    ("magic" | uint32L).exmap[Long](
      s => if (s == magic) Successful(s) else Failure(scodec.Err("magic did not match.")),
      m => if(m == magic) Successful(m) else Failure(scodec.Err("magic did not match."))
    )

  def payLoadCodec(cmd: String, length: Long, chk: Long, version: Int) = {
    val cdc = MessageCompanion.byCommand.get(cmd.trim).map(_.codec(version))
    bits.exmap[Message](
      { b =>
        val (payload, rest) = b.splitAt(length * 8)
        if (!rest.isEmpty) {
          Failure(scodec.Err("payload length did not match."))
        } else if (Util.checksum(payload.toByteVector) == chk) {
          cdc.fold[Attempt[Message]](Failure(scodec.Err(s"message: $cmd not recognized")))(_.decode(payload).map(_.value))
        } else {
          Failure(scodec.Err("checksum didnt match."))
        }
      },
      codecMsg(version, _)
    )
  }
  def codecMsg(version: Int, msg: Message) = msg.companion.codec(version).encode(msg)

  def codec(magic: Long, version: Int): Codec[Message] = {
    def encode(msg: Message): Attempt[BitVector] = {
      for {
        magic <- magicCodec(magic).encode(magic)
        cmd <- commandCodec.encode(msg.companion.command) //How can i get the discriminator value out?
        payload <- codecMsg(version, msg)
        length <- uint32L.encode(payload.length / 8)
        chksum <- uint32L.encode(Util.checksum(payload.toByteVector))
      } yield magic ++ cmd ++ length ++ chksum ++ payload
    }

    def decode(bits: BitVector): Attempt[DecodeResult[Message]] = {
      (for {
        magic <- magicCodec(magic)
        cmd <- fixedSizeBytes(12, ascii) //this is the label for finding the payload in Msg
        length <- uint32L
        chk <- uint32L
        payload <- payLoadCodec(cmd.trim, length, chk, version)
      } yield payload)
        .decode(bits)
    }

    Codec[Message](encode _, decode _)
  }
}

object MessageCompanion {
  val all: Set[MessageCompanion[_ <: Message]] =
    Set(
      Addr, Alert, Block, CmptcBlock, GetAddr, GetBlocks,
      GetData, GetHeaders, Headers, Inv, MemPool,
      NotFound, Ping, Pong, Reject, SendCmpct,
      SendHeaders, Tx, Verack, Version
    )

  val byCommand: Map[String, MessageCompanion[_ <: Message]] = {
    println(all.map(_.command))
    require(all.map(_.command).size == all.size, "Type headers must be unique.")
    all.map { companion => companion.command -> companion }.toMap
  }
}

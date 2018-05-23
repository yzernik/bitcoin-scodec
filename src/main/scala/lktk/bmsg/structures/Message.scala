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
        cdc.fold[Attempt[Message]](Failure(scodec.Err(s"message: $cmd not recognized"))) {
          s => s.decode(b).flatMap { p =>
            if (!p.remainder.isEmpty) {
              Failure(scodec.Err("payload length did not match."))
            } else if (Util.checksum(b.toByteVector) == chk) {
              Successful(p.value)
            } else {
              Failure(scodec.Err("checksum did not match."))
            }
          }
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

//List all Message types
object MessageCompanion {
  val all: Set[MessageCompanion[_ <: Message]] =
    Set(
      Addr, Alert, Block, BlockTxn, CmpctBlock, FeeFilter,
      FilterAdd, FilterLoad, FilterClear, MerkleBlock,
      GetAddr, GetBlocks, GetData, GetHeaders, GetUtxo, GetBlockTxn,
      Headers, Inv, MemPool, NotFound, Ping, Pong, Reject,
      SendCmpct, SendHeaders, Tx0, Utxos, Verack, Version
    )

  val byCommand: Map[String, MessageCompanion[_ <: Message]] = {
    require(all.map(_.command).size == all.size, "Type headers must be unique.")
    all.map { companion => companion.command -> companion }.toMap
  }
}

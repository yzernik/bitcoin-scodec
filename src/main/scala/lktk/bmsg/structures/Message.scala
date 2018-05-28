package lktk.bmsg.structures

import lktk.bmsg.messages._
import lktk.bmsg.util.Util

import scala.language.{existentials, implicitConversions}

import scodec.Attempt.{Failure, Successful}
import scodec.Codec
import scodec.bits.BitVector
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

  def decodeHeader(bits: BitVector, magic: Long, version: Int) =
    for {
      m <- uint32L.decode(bits).flatMap { mg =>
        if (mg.value == magic) Successful(mg) else Failure(scodec.Err("magic did not match."))
      }
      command <- commandCodec.decode(m.remainder)
      length <- uint32L.decode(command.remainder)
      chksum <- uint32L.decode(length.remainder)
      (payload, _) = chksum.remainder.splitAt(length.value * 8)
    } yield (command.value.trim, length, chksum.value, payload)

  def decodePayload(payload: BitVector, version: Int, chksum: Long, command: String) =
    MessageCompanion.byCommand.get(command)
      .map(_.codec(version).decode(payload).flatMap { p =>
        if (!p.remainder.isEmpty) {
          Failure(scodec.Err("payload length did not match."))
        } else if (Util.checksum(payload.toByteVector) == chksum) {
          Successful(p)
        } else {
          Failure(scodec.Err("checksum did not match."))
        }})
      .getOrElse(Failure(scodec.Err(s"message: $command not recognized")))

  def codec(magic: Long, version: Int): Codec[Message] = {

    def encode(msg: Message) = {
      val c = msg.companion.codec(version)
      for {
        magic <- uint32L.encode(magic)
        command <- commandCodec.encode(msg.companion.command)
        payload <- c.encode(msg)
        length <- uint32L.encode(payload.length / 8)
        chksum <- uint32L.encode(Util.checksum(payload.toByteVector))
      } yield magic ++ command ++ length ++ chksum ++ payload
    }

    def decode(bits: BitVector) =
      for {
        metadata <- decodeHeader(bits, magic, version)
        (command, length, chksum, payload) = metadata
        msg <- decodePayload(payload, version, chksum, command)
      } yield msg

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

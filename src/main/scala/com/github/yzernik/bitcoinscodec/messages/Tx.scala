package com.github.yzernik.bitcoinscodec.messages

import scodec.bits.ByteVector
import scodec.Codec
import scodec.codecs
import scalaz.std.anyVal.unitInstance
import scodec.bits.BitVector
import scodec.Codec
import scodec.codecs._
import shapeless._
import com.github.yzernik.bitcoinscodec.structures._
import com.github.yzernik.bitcoinscodec.structures.Message

case class Tx(
  version: Long,
  tx_in: List[TxIn],
  tx_out: List[TxOut],
  lock_time: Long) extends Message {
  type E = Tx
  def companion = Tx
}

object Tx extends MessageCompanion[Tx] {
  def codec(version: Int) = {
    ("version" | uint32L) ::
      ("tx_in" | VarList.varList(Codec[TxIn])) ::
      ("tx_out" | VarList.varList(Codec[TxOut])) ::
      ("lock_time" | uint32L)
  }.as[Tx]
  def command = "tx"
}
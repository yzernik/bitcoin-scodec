package com.oohish.bitcoinscodec.messages

import scodec.bits.ByteVector
import scodec.Codec
import scodec.codecs
import scalaz.std.anyVal.unitInstance
import scodec.bits.BitVector
import scodec.Codec
import scodec.codecs._
import shapeless._
import com.oohish.bitcoinscodec.structures._
import com.oohish.bitcoinscodec.structures.Message.Message

case class Tx(
  version: Long,
  tx_in: List[TxIn],
  tx_out: List[TxOut],
  lock_time: Long) extends Message {
  type E = Tx
  def codec = Tx.codec
}

object Tx {
  import VarList._

  implicit val codec = {
    ("version" | uint32L) ::
      ("tx_in" | VarList.varList(Codec[TxIn])) ::
      ("tx_out" | VarList.varList(Codec[TxOut])) ::
      ("lock_time" | uint32L)
  }.as[Tx]

}
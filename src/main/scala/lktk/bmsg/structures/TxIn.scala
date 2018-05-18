package lktk.bmsg.structures

import scodec.Codec
import scodec.bits.ByteVector
import scodec.codecs._

case class TxIn(
  previousOutput: OutPoint,
  sigScript: ByteVector,
  sequence: Long
)

object TxIn {

  val scriptCodec = {
    val countCodec = VarInt.varIntCodec.xmap(_.toInt, (i: Int) => i.toLong)
    variableSizeBytes(countCodec, bytes)
  }

  implicit val codec: Codec[TxIn] = {
    ("previousOutput" | Codec[OutPoint]) ::
      ("sigScript" | scriptCodec) ::
      ("sequence" | uint32)
  }.as[TxIn]

}

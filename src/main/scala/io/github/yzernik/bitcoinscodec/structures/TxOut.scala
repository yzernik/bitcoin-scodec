package io.github.yzernik.bitcoinscodec.structures

import scodec.Codec
import scodec.bits.ByteVector
import scodec.codecs._

case class TxOut(
  value: Long,
  pk_script: ByteVector)

object TxOut {

  val scriptCodec = {
    val countCodec = VarInt.varIntCodec.xmap(_.toInt, (i: Int) => i.toLong)
    variableSizeBytes(countCodec, bytes)
  }

  implicit val codec: Codec[TxOut] = {
    ("value" | int64) ::
      ("pk_script" | scriptCodec)
  }.as[TxOut]

}

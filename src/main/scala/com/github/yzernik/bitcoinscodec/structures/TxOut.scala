package com.github.yzernik.bitcoinscodec.structures

import scodec.Codec
import scodec.ValueCodecEnrichedWithHListSupport
import scodec.bits.ByteVector
import scodec.codecs.StringEnrichedWithCodecNamingSupport
import scodec.codecs.bytes
import scodec.codecs.int64
import scodec.codecs.variableSizeBytes

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

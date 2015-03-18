package com.github.yzernik.bitcoinscodec.structures

import scodec.Codec
import scodec.HListCodecEnrichedWithHListSupport
import scodec.ValueCodecEnrichedWithHListSupport
import scodec.bits.ByteVector
import scodec.codecs.StringEnrichedWithCodecNamingSupport
import scodec.codecs.bytes
import scodec.codecs.uint32
import scodec.codecs.variableSizeBytes

case class TxIn(
  previous_output: OutPoint,
  sig_script: ByteVector,
  sequence: Long)

object TxIn {

  val scriptCodec = {
    val countCodec = VarInt.varIntCodec.xmap(_.toInt, (i: Int) => i.toLong)
    variableSizeBytes(countCodec, bytes)
  }

  implicit val codec: Codec[TxIn] = {
    ("previous_output" | Codec[OutPoint]) ::
      ("sig_script" | scriptCodec) ::
      ("sequence" | uint32)
  }.as[TxIn]

}

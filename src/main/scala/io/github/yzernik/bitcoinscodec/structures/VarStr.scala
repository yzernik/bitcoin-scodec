package io.github.yzernik.bitcoinscodec.structures

import scala.language.implicitConversions

import scodec.Codec
import scodec.codecs.ascii
import scodec.codecs.variableSizeBytes

object VarStr {

  def apply(): Codec[String] = codec

  implicit val codec: Codec[String] = {
    val countCodec = VarInt.varIntCodec.xmap(_.toInt, (i: Int) => i.toLong)
    variableSizeBytes(countCodec, ascii)
  }
}
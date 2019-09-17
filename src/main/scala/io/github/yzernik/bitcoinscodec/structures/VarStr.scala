package io.github.yzernik.bitcoinscodec.structures

import scodec.Codec
import scodec.codecs.{ascii, variableSizeBytes}

import scala.language.implicitConversions

object VarStr {

  def apply(): Codec[String] = codec

  implicit val codec: Codec[String] = {
    val countCodec = VarInt.varIntCodec.xmap(_.toInt, (i: Int) => i.toLong)
    variableSizeBytes(countCodec, ascii)
  }
}
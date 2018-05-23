package lktk.bmsg.structures

import scala.language.implicitConversions

import scodec.Codec
import scodec.codecs.ascii
import scodec.codecs.variableSizeBytes

object VarStr {

  implicit val codec: Codec[String] = {
    val countCodec = VarInt.varIntCodec.xmap(_.toInt, (i: Int) => i.toLong)
    variableSizeBytes(countCodec, ascii)
  }
}
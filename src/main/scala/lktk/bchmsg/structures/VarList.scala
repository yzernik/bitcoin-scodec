package lktk.bchmsg.structures

import scala.language.implicitConversions

import scodec.Codec
import scodec.codecs.listOfN

object VarList {

  implicit def varList[A](codec: Codec[A]): Codec[List[A]] = {
    val countCodec = VarInt.varIntCodec.xmap(_.toInt, (i: Int) => i.toLong)
    listOfN(countCodec, codec)
  }
}
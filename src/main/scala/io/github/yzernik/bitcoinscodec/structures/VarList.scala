package io.github.yzernik.bitcoinscodec.structures

import scodec.Codec
import scodec.codecs.listOfN

import scala.language.implicitConversions

object VarList {

  def apply[A](codec: Codec[A]): Codec[List[A]] =
    varList(codec)

  def varList[A](codec: Codec[A]): Codec[List[A]] = {
    val countCodec = VarInt().xmap(_.toInt, (i: Int) => i.toLong)
    listOfN(countCodec, codec)
  }
}
package io.github.yzernik.bitcoinscodec.structures

import scodec.Codec
import scodec.bits.ByteVector
import scodec.bits.HexStringSyntax
import scodec.codecs.{bytes, variableSizeBytes}

case class Script(value: ByteVector) {

  override def toString = s"${value.toHex}"
}

object Script {
  implicit val codec:Codec[Script] = {
    val countCodec = VarInt.varIntCodec.xmap(_.toInt, (i: Int) => i.toLong)
    variableSizeBytes(countCodec, bytes).as[Script]
  }
}
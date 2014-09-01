package com.oohish.bitcoinz.messages

import scodec.bits.ByteVector
import scodec.Codec
import scodec.codecs

import scalaz.std.anyVal.unitInstance
import scodec.bits.BitVector
import scodec.Codec
import scodec.codecs._
import shapeless._

case class IPV4(value: Int) {
  override def toString = ByteVector.fromInt(value).toIterable.map { b => 0xff & b.toInt }.mkString(".")
}

object IPV4 {
  val ipv4Pad = ByteVector(0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0xFF, 0xFF).toBitVector

  implicit val codec: Codec[IPV4] =
    ("pad" | constant(ipv4Pad)) ~>
      ("ip" | codecs.int32.xmap[IPV4](v => IPV4(v), _.value))
}
package com.oohish.bitcoinz.messages

import scodec.bits.ByteVector
import scodec.Codec
import scodec.codecs

import scalaz.std.anyVal.unitInstance
import scodec.bits.BitVector
import scodec.Codec
import scodec.codecs._
import shapeless._

case class IPV4(value: Int) extends IP {
  override def toString = ByteVector.fromInt(value).toIterable.map { b => 0xff & b.toInt }.mkString(".")
}

object IPV4 {

  implicit val codec: Codec[IPV4] = codecs.int32.xmap[IPV4](v => IPV4(v), _.value)
}
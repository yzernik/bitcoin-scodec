package com.oohish.bitcoinscodec.messages

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

  /** Creates an IPV4 from ip fields. */
  def apply(f1: Int, f2: Int, f3: Int, f4: Int): IPV4 =
    IPV4((f1 << 24) + (f2 << 16) + (f3 << 8) + f4)

  /** Creates an IPV4 from string. */
  def apply(s: String): IPV4 = {
    val fields = s.split('.')
    assert(fields.length == 4)
    val digs = fields.map(_.toInt)
    IPV4(digs(0), digs(1), digs(2), digs(3))
  }

  implicit val codec: Codec[IPV4] = codecs.int32.xmap[IPV4](v => IPV4(v), _.value)
}
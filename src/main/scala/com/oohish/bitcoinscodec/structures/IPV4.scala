package com.oohish.bitcoinscodec.structures

import scodec.bits.ByteVector
import scodec.Codec
import scodec.codecs._

case class IPV4(value: ByteVector) {
  require(value.length == 4)

  override def toString = value.toIterable.map { b => 0xff & b.toInt }.mkString(".")
}

object IPV4 {

  /** Creates an IPV4 from string. */
  def apply(s: String): IPV4 = {
    val fields = s.split('.')
    assert(fields.length == 4)
    val digs = fields.map(_.toInt).map(_.toByte)
    IPV4(ByteVector(digs))
  }

  implicit val codec: Codec[IPV4] =
    bytes(4).xmap[IPV4](v => IPV4(v), _.value)
}
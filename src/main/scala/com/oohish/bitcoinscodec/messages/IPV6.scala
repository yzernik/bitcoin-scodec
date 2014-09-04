package com.oohish.bitcoinscodec.messages

import scodec.bits.ByteVector
import scodec.Codec
import scodec.codecs
import scodec.bits._

case class IPV6(value: ByteVector) {
  require(value.length == 16)

  override def toString = value.toIterable.grouped(2)
    .map { dig =>
      digitString(dig)
    }.mkString(":")

  def digitString(digit: Iterable[Byte]): String = {
    digit.map {
      "%02x".format(_)
    }.mkString("")
  }
}

object IPV6 {
  implicit val codec: Codec[IPV6] =
    codecs.bytes(16).xmap[IPV6](v => IPV6(v), _.value)
}
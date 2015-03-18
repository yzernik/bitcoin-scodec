package com.github.yzernik.bitcoinscodec.structures

import scodec.Codec
import scodec.bits.ByteVector
import scodec.codecs.bytes

case class Hash(value: ByteVector) {
  require(value.size == 32)

  override def toString = s"${value.toHex}"
}

object Hash {
  implicit val codec: Codec[Hash] = bytes(32)
    .xmap(b => Hash.apply(b.reverse), _.value.reverse)
}
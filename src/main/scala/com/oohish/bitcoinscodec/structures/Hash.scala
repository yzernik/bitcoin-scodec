package com.oohish.bitcoinscodec.structures

import scodec.Codec
import scodec.codecs._
import scodec.bits.ByteVector

case class Hash(value: ByteVector) {
  require(value.size == 32)

  override def toString = s"${value.toHex}"
}

object Hash {
  implicit val codec: Codec[Hash] = bytes(32)
    .xmap(b => Hash.apply(b.reverse), _.value.reverse)
}
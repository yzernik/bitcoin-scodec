package com.oohish.bitcoinscodec.structures

import scodec.Codec
import scodec.codecs._
import scodec.bits.ByteVector

case class Hash(value: ByteVector) {
  require(value.size == 32)
}

object Hash {
  implicit val codec: Codec[Hash] = bytes(32).xmap(Hash.apply, _.value)
}
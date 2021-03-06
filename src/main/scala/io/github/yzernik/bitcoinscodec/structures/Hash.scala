package io.github.yzernik.bitcoinscodec.structures

import scodec.Codec
import scodec.bits.ByteVector
import scodec.codecs.bytes

case class Hash(value: ByteVector) {
  require(value.size == 32)

  override def toString = s"Hash(0x${value.toHex})"
}

object Hash {
  implicit val codec: Codec[Hash] = bytes(32)
    .xmap(b => Hash.apply(b.reverse), _.value.reverse)

  val NULL = Hash(ByteVector.fill(32)(0))
}
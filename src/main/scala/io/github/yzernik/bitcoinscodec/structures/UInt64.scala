package io.github.yzernik.bitcoinscodec.structures


import scodec.Codec
import scodec.bits.ByteVector
import scodec.codecs.bytes

import scala.math.BigInt.{int2bigInt, long2bigInt}

case class UInt64(value: ByteVector) {
  require(value.size == 8)

  override def toString = s"UInt64(0x${value.toHex})"
}

object UInt64 {

  def apply(value: Long): UInt64 = {
    require(value >= 0)
    val bytes = longToByteVector(value)
    UInt64(bytes)
  }

  def apply(value: BigInt): UInt64 = {
    require(value >= 0)
    val bytes = bigIntToByteVector(value)
    UInt64(bytes)
  }

  def longToByteVector(value: Long): ByteVector = {
    val bigInt = longToBigInt(value)
    bigIntToByteVector(bigInt)
  }

  def bigIntToByteVector(value: BigInt): ByteVector = {
    val byteVector = ByteVector(value.toByteArray)
    // pad the byte vector
    ByteVector.fill(8 - byteVector.length)(0) ++ byteVector
  }

  def byteVectorToLong(byteVector: ByteVector): Long = {
    val bigInt = BigInt(byteVector.toArray)
    bigIntToLong(bigInt)
  }

  def longToBigInt(unsignedLong: Long): BigInt =
    (BigInt(unsignedLong >>> 1) << 1) + (unsignedLong & 1)

  def bigIntToLong(n: BigInt): Long = {
    val smallestBit = (n & 1).toLong
    ((n >> 1).toLong << 1) | smallestBit
  }

  implicit val codec: Codec[UInt64] = bytes(8)
    .xmap(b => UInt64.apply(b.reverse), _.value.reverse)
}

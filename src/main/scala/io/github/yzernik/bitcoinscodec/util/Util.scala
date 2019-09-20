package io.github.yzernik.bitcoinscodec.util

import java.nio.{ByteBuffer, ByteOrder}
import java.security.{MessageDigest, SecureRandom}

import io.github.yzernik.bitcoinscodec.structures.{Hash, UInt64}
import scodec.bits.{BitVector, ByteVector}
import scodec.codecs._

object Util {

  def checksum(data: ByteVector): Long = {
    val hash = hashBytes(data.toArray)
    val padding: Array[Byte] = Array.fill(4)(0)
    val byteBuffer = ByteBuffer.wrap(hash.slice(0, 4) ++ padding)
      .order(ByteOrder.LITTLE_ENDIAN)
    byteBuffer.getLong()
  }

  def hash(data: ByteVector): Hash = {
    val bytes = data.toArray
    val hash = hashBytes(bytes)
    Hash(ByteVector(hash).reverse)
  }

  private def hashBytes(bytes: Array[Byte]): Array[Byte] = {
    val messageDigest = MessageDigest.getInstance("SHA-256")
    val hash1 = messageDigest.digest(bytes)
    val hash2 = messageDigest.digest(hash1)
    hash2
  }

  def generateNonce32(): Long = {
    val bytes = randomBytes(4)
    uint32L.decode(BitVector(bytes)).toOption.get.value
  }

  def generateNonce64(): UInt64 = {
    val bytes = randomBytes(8)
    UInt64.codec.decode(BitVector(bytes)).toOption.get.value
  }

  private def randomBytes(length: Int): Array[Byte] = {
    val bytes = new Array[Byte](length)
    SecureRandom.getInstanceStrong.nextBytes(bytes)
    bytes
  }

}
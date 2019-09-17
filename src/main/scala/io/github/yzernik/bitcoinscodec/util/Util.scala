package io.github.yzernik.bitcoinscodec.util

import scodec.codecs._
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.security.MessageDigest

import scodec.bits.{BitVector, ByteVector}
import io.github.yzernik.bitcoinscodec.structures.{Hash, UInt64}

object Util {

  def checksum(data: ByteVector): Long = {
    val hash = hashBytes(data.toArray)
    val padding: Array[Byte] = Array.fill(4)(0)
    val byteBuffer = ByteBuffer.wrap(hash.slice(0, 4) ++ padding)
      .order(ByteOrder.LITTLE_ENDIAN)
    byteBuffer.getLong()
  }

  def hash(bytes: Array[Byte]): Hash = {
    val hash = hashBytes(bytes)
    Hash(ByteVector(hash).reverse)
  }

  def hashBytes(bytes: Array[Byte]): Array[Byte] = {
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

  private def randomBytes(length: Int): Array[Byte] =
    Array.fill(length)((scala.util.Random.nextInt(256) - 128).toByte)

}
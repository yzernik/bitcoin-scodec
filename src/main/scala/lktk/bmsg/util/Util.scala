package lktk.bmsg.util

import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.security.MessageDigest

import lktk.bmsg.structures.Hash
import scodec.bits.ByteVector


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

}
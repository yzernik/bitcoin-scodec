package io.github.yzernik.bitcoinscodec.util

import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.security.MessageDigest

import scodec.bits.ByteVector

object Util {

  def checksum(data: ByteVector): Long = {
    val messageDigest = MessageDigest.getInstance("SHA-256")
    val hash1 = messageDigest.digest(data.toArray)
    val hash2 = messageDigest.digest(hash1)
    val padding: Array[Byte] = Array.fill(4)(0)
    val byteBuffer = ByteBuffer.wrap(hash2.slice(0, 4) ++ padding)
      .order(ByteOrder.LITTLE_ENDIAN)
    byteBuffer.getLong()
  }

}
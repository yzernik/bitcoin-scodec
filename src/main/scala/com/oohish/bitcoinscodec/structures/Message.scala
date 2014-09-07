package com.oohish.bitcoinscodec.structures

import scala.language.implicitConversions
import scodec.Codec
import scodec.codecs._
import scodec.bits._
import com.oohish.bitcoinscodec.structures._
import com.oohish.bitcoinscodec.messages._
import java.security.MessageDigest
import java.nio.ByteBuffer
import java.nio.ByteOrder

object Message {

  trait Message { self =>
    type E >: self.type <: Message
    def codec: Codec[E]
  }

  val commands: Map[String, Codec[_ <: Message]] = Map(
    "version" -> Version.codec,
    "verack" -> Verack.codec,
    "addr" -> Addr.codec,
    "ping" -> Ping.codec,
    "pong" -> Pong.codec)

  def padCommand(command: String) = {
    ByteVector(command.getBytes()) ++
      ByteVector.fill(12 - command.length())(0)
  }

  val payloadCodec: Codec[Codec[_ <: Message]] = mappedEnum(fixedSizeBytes(12, ascii),
    commands.map(_.swap))

  def checksum(data: ByteVector): Long = {
    val messageDigest = MessageDigest.getInstance("SHA-256")
    val hash1 = messageDigest.digest(data.toArray)
    val hash2 = messageDigest.digest(hash1)
    val padding: Array[Byte] = Array.fill(4)(0)
    val byteBuffer = ByteBuffer.wrap(hash2.slice(0, 4) ++ padding)
      .order(ByteOrder.LITTLE_ENDIAN)
    byteBuffer.getLong()
  }

  implicit def codec(magic: Long): Codec[Message] = {
    new Codec[Message] {
      def encode(msg: Message) = {
        val c = msg.codec
        for {
          magic <- uint32L.encode(magic)
          command <- payloadCodec.encode(c)
          payload <- c.encode(msg)
          length <- uint32L.encode(payload.length)
          chksum <- uint32L.encode(checksum(payload.toByteVector))
        } yield magic ++ command ++ length ++ chksum ++ payload
      }
      def decode(bits: BitVector) = {
        // TODO
        val c = Codec[Verack]
        c.decode(bits)
      }
    }
  }

}

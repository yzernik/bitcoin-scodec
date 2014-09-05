package com.oohish.bitcoinscodec.messages

import scala.language.implicitConversions

import scalaz.{ \/, \/-, -\/, Monad, Monoid }
import scodec.Codec
import scodec.codecs._
import scodec.bits._
import com.oohish.bitcoinscodec.messages.Message.Message
import com.oohish.bitcoinscodec.structures._

object Message {

  trait Message

  val payloadCodec: Codec[Codec[_ <: Message]] = mappedEnum(fixedSizeBytes(12, ascii),
    Version.codec -> "version",
    Verack.codec -> "verack",
    Addr.codec -> "addr",
    Ping.codec -> "ping",
    Pong.codec -> "pong")

  implicit def codec(magic: Long): Codec[_ <: Message] = {

    new Codec[Message] {
      def encode(msg: Message) = ???
      def decode(bits: BitVector) = ???
    }

  }

}

package com.oohish.bitcoinscodec.messages

import scodec.Codec
import scodec.codecs._
import scodec.bits.ByteVector
import com.oohish.bitcoinscodec.messages.Message.Message

case class Verack() extends Message

object Verack {
  implicit val codec: Codec[Verack] = provide(Verack())
}
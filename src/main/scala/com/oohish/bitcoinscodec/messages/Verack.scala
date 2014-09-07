package com.oohish.bitcoinscodec.messages

import scodec.Codec
import scodec.codecs._
import scodec.bits.ByteVector
import com.oohish.bitcoinscodec.structures.Message.Message

case class Verack() extends Message {
  type E = Verack
  def codec = Verack.codec
}

object Verack {
  implicit val codec: Codec[Verack] = provide(Verack())
}
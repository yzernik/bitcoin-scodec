package com.oohish.bitcoinscodec.messages

import scodec.Codec
import scodec.codecs._
import scodec.bits.ByteVector
import com.oohish.bitcoinscodec.structures.Message

case class Verack() extends Message {
  type E = Verack
  def codec = Verack.codec
  def command = "verack"
}

object Verack {
  implicit val codec: Codec[Verack] = provide(Verack())
}
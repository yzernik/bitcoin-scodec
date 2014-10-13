package com.oohish.bitcoinscodec.messages

import scodec.Codec
import scodec.codecs._
import scodec.bits.ByteVector
import com.oohish.bitcoinscodec.structures.Message
import com.oohish.bitcoinscodec.structures.MessageCompanion

case class Verack() extends Message {
  type E = Verack
  def companion = Verack
}

object Verack extends MessageCompanion[Verack] {
  def codec(version: Int): Codec[Verack] = provide(Verack())
  def command = "verack"
}
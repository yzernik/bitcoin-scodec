package com.github.yzernik.bitcoinscodec.messages

import scodec.Codec
import scodec.codecs._
import scodec.bits.ByteVector
import com.github.yzernik.bitcoinscodec.structures.Message
import com.github.yzernik.bitcoinscodec.structures.MessageCompanion

case class Verack() extends Message {
  type E = Verack
  def companion = Verack
}

object Verack extends MessageCompanion[Verack] {
  def codec(version: Int): Codec[Verack] = provide(Verack())
  def command = "verack"
}
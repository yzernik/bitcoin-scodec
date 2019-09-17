package io.github.yzernik.bitcoinscodec.messages

import io.github.yzernik.bitcoinscodec.structures.{Message, MessageCompanion}
import scodec.Codec
import scodec.codecs.provide

case class Verack() extends Message {
  type E = Verack
  def companion = Verack
}

object Verack extends MessageCompanion[Verack] {
  override def codec(version: Int): Codec[Verack] = provide(Verack())
  override def command = "verack"
}
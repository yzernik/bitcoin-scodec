package lktk.bchmsg.messages

import lktk.bchmsg.structures.{Message, MessageCompanion}

import scodec.Codec
import scodec.codecs.provide

case class Verack() extends Message {
  type E = Verack
  def companion = Verack
}

object Verack extends MessageCompanion[Verack] {
  def codec(version: Int): Codec[Verack] = provide(Verack())
  def command = "verack"
}
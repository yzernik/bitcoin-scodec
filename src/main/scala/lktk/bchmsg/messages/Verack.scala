package lktk.bchmsg.messages

import lktk.bchmsg.structures.Message
import lktk.bchmsg.structures.MessageCompanion
import lktk.bchmsg.structures.{Message, MessageCompanion}

case class Verack() extends Message {
  type E = Verack
  def companion = Verack
}

object Verack extends MessageCompanion[Verack] {
  def codec(version: Int): Codec[Verack] = provide(Verack())
  def command = "verack"
}
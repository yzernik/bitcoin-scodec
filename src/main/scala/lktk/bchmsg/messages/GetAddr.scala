package lktk.bchmsg.messages

import lktk.bchmsg.structures.Message
import lktk.bchmsg.structures.MessageCompanion
import lktk.bchmsg.structures.{Message, MessageCompanion}

case class GetAddr() extends Message {
  type E = GetAddr
  def companion = GetAddr
}

object GetAddr extends MessageCompanion[GetAddr] {
  def codec(version: Int): Codec[GetAddr] = provide(GetAddr())
  def command = "getaddr"
}
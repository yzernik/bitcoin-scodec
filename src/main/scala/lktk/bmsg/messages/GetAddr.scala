package lktk.bmsg.messages

import lktk.bmsg.structures.{Message, MessageCompanion}

import scodec.Codec
import scodec.codecs.provide

case class GetAddr() extends Message {
  type E = GetAddr
  def companion = GetAddr
}

object GetAddr extends MessageCompanion[GetAddr] {
  def codec(version: Int): Codec[GetAddr] = provide(GetAddr())
  def command = "getaddr"
}
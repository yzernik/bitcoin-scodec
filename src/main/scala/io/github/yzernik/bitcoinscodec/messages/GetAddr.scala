package io.github.yzernik.bitcoinscodec.messages

import io.github.yzernik.bitcoinscodec.structures.{Message, MessageCompanion}
import scodec.Codec
import scodec.codecs.provide

case class GetAddr() extends Message {
  type E = GetAddr
  def companion = GetAddr
}

object GetAddr extends MessageCompanion[GetAddr] {
  override def codec(version: Int): Codec[GetAddr] = provide(GetAddr())
  override def command = "getaddr"
}
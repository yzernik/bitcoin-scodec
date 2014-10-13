package com.oohish.bitcoinscodec.messages

import scodec.Codec
import scodec.codecs._
import scodec.bits.ByteVector
import com.oohish.bitcoinscodec.structures.Message
import com.oohish.bitcoinscodec.structures.MessageCompanion

case class GetAddr() extends Message {
  type E = GetAddr
  def companion = GetAddr
}

object GetAddr extends MessageCompanion[GetAddr] {
  def codec(version: Int): Codec[GetAddr] = provide(GetAddr())
  def command = "getaddr"
}
package io.github.yzernik.bitcoinscodec.messages

import io.github.yzernik.bitcoinscodec.structures.Message
import io.github.yzernik.bitcoinscodec.structures.MessageCompanion
import io.github.yzernik.bitcoinscodec.structures.VarList

import scodec.Codec

case class Headers(invs: List[Block]) extends Message {
  type E = Headers
  def companion = Headers
}

object Headers extends MessageCompanion[Headers] {
  def codec(version: Int): Codec[Headers] =
    VarList.varList(Block.codec(version)).as[Headers]
  def command = "headers"
}
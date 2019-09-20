package io.github.yzernik.bitcoinscodec.messages

import io.github.yzernik.bitcoinscodec.structures.{Message, MessageCompanion, VarList}
import scodec.Codec

case class Headers(headers: List[Block]) extends Message {
  require(headers.forall(_.txs.isEmpty))

  type E = Headers
  override def companion = Headers
}

object Headers extends MessageCompanion[Headers] {
  override def codec(version: Int): Codec[Headers] =
    VarList(Block.codec(version)).as[Headers]
  override def command = "headers"
}
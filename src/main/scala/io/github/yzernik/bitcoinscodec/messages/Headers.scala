package io.github.yzernik.bitcoinscodec.messages

import io.github.yzernik.bitcoinscodec.structures.{Message, MessageCompanion, VarList}
import scodec.Codec

case class Headers(invs: List[Block]) extends Message {
  require(invs.forall(_.txs.isEmpty))

  type E = Headers
  def companion = Headers
}

object Headers extends MessageCompanion[Headers] {
  override def codec(version: Int): Codec[Headers] =
    VarList(Block.codec(version)).as[Headers]
  override def command = "headers"
}
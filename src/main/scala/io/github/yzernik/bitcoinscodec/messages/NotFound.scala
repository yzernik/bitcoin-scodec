package io.github.yzernik.bitcoinscodec.messages

import io.github.yzernik.bitcoinscodec.structures.{InvVect, Message, MessageCompanion, VarList}
import scodec.Codec

case class NotFound(invs: List[InvVect]) extends Message {
  type E = NotFound
  def companion = NotFound
}

object NotFound extends MessageCompanion[NotFound] {
  override def codec(version: Int): Codec[NotFound] =
    VarList(Codec[InvVect]).as[NotFound]
  override def command = "notfound"
}
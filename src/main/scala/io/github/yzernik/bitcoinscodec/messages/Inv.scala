package io.github.yzernik.bitcoinscodec.messages

import io.github.yzernik.bitcoinscodec.structures.{InvVect, Message, MessageCompanion, VarList}
import scodec.Codec

case class Inv(invs: List[InvVect]) extends Message {
  type E = Inv
  def companion = Inv
}

object Inv extends MessageCompanion[Inv] {
  override def codec(version: Int): Codec[Inv] =
    VarList(Codec[InvVect]).as[Inv]
  override def command = "inv"
}
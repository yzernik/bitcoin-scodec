package lktk.bchmsg.messages

import lktk.bchmsg.structures.{InvVect, Message, MessageCompanion, VarList}

import scodec.Codec

case class NotFound(invs: List[InvVect]) extends Message {
  type E = NotFound
  def companion = NotFound
}

object NotFound extends MessageCompanion[NotFound] {
  def codec(version: Int): Codec[NotFound] =
    VarList.varList(Codec[InvVect]).as[NotFound]
  def command = "notfound"
}
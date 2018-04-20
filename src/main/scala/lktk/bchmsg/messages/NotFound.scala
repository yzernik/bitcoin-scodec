package lktk.bchmsg.messages

import lktk.bchmsg.structures.InvVect
import lktk.bchmsg.structures.Message
import lktk.bchmsg.structures.MessageCompanion
import lktk.bchmsg.structures.{InvVect, Message, MessageCompanion, VarList}

case class NotFound(invs: List[InvVect]) extends Message {
  type E = NotFound
  def companion = NotFound
}

object NotFound extends MessageCompanion[NotFound] {
  def codec(version: Int): Codec[NotFound] =
    VarList.varList(Codec[InvVect]).as[NotFound]
  def command = "notfound"
}
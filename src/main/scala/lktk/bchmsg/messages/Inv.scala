package lktk.bchmsg.messages

import lktk.bchmsg.structures.InvVect
import lktk.bchmsg.structures.Message
import lktk.bchmsg.structures.MessageCompanion
import lktk.bchmsg.structures.VarList
import lktk.bchmsg.structures.{InvVect, Message, MessageCompanion, VarList}

case class Inv(invs: List[InvVect]) extends Message {
  type E = Inv
  def companion = Inv
}

object Inv extends MessageCompanion[Inv] {
  def codec(version: Int): Codec[Inv] =
    VarList.varList(Codec[InvVect]).as[Inv]
  def command = "inv"
}
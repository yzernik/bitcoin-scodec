package lktk.bmsg.messages

import lktk.bmsg.structures.{InvVect, Message, MessageCompanion, VarList}

import scodec.Codec

case class Inv(invs: List[InvVect]) extends Message {
  type E = Inv
  def companion = Inv
}

object Inv extends MessageCompanion[Inv] {
  def codec(version: Int): Codec[Inv] =
    VarList.varList(Codec[InvVect]).as[Inv]
  def command = "inv"
}
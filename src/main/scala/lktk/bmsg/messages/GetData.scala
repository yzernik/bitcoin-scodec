package lktk.bmsg.messages

import lktk.bmsg.structures.{InvVect, Message, MessageCompanion, VarList}

import scodec.Codec

case class GetData(invs: List[InvVect]) extends Message {
  type E = GetData
  def companion = GetData
}

object GetData extends MessageCompanion[GetData] {
  def codec(version: Int): Codec[GetData] =
    VarList.varList(Codec[InvVect]).as[GetData]
  def command = "getdata"
}
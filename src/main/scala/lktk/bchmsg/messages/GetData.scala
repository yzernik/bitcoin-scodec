package lktk.bchmsg.messages

import lktk.bchmsg.structures.InvVect
import lktk.bchmsg.structures.Message
import lktk.bchmsg.structures.MessageCompanion
import lktk.bchmsg.structures.{InvVect, Message, MessageCompanion, VarList}

case class GetData(invs: List[InvVect]) extends Message {
  type E = GetData
  def companion = GetData
}

object GetData extends MessageCompanion[GetData] {
  def codec(version: Int): Codec[GetData] =
    VarList.varList(Codec[InvVect]).as[GetData]
  def command = "getdata"
}
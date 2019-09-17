package io.github.yzernik.bitcoinscodec.messages

import io.github.yzernik.bitcoinscodec.structures.{InvVect, Message, MessageCompanion, VarList}
import scodec.Codec

case class GetData(invs: List[InvVect]) extends Message {
  type E = GetData
  def companion = GetData
}

object GetData extends MessageCompanion[GetData] {
  override def codec(version: Int): Codec[GetData] =
    VarList(Codec[InvVect]).as[GetData]
  override def command = "getdata"
}
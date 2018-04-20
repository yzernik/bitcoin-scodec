package lktk.bchmsg.messages

import lktk.bchmsg.structures.Message
import lktk.bchmsg.structures.MessageCompanion
import lktk.bchmsg.structures.VarList
import lktk.bchmsg.structures.{Message, MessageCompanion, VarList}

case class Headers(invs: List[Block]) extends Message {
  type E = Headers
  def companion = Headers
}

object Headers extends MessageCompanion[Headers] {
  def codec(version: Int): Codec[Headers] =
    VarList.varList(Block.codec(version)).as[Headers]
  def command = "headers"
}
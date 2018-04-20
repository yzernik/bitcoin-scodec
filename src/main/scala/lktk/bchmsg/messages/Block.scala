package lktk.bchmsg.messages

import lktk.bchmsg.structures.BlockHeader
import lktk.bchmsg.structures.Message
import lktk.bchmsg.structures.MessageCompanion
import lktk.bchmsg.structures.VarList
import lktk.bchmsg.structures.{BlockHeader, Message, MessageCompanion, VarList}

case class Block(
  block_header: BlockHeader,
  txs: List[Tx]) extends Message {
  type E = Block
  def companion = Block
}

object Block extends MessageCompanion[Block] {
  def codec(version: Int) = {
    ("block_header" | Codec[BlockHeader]) ::
      ("txs" | VarList.varList(Tx.codec(version)))
  }.as[Block]
  def command = "block"
}

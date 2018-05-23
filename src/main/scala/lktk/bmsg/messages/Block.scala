package lktk.bmsg.messages

import lktk.bmsg.structures._

import scodec.Codec
import scodec.codecs._

case class Block(
  block_header: BlockHeader,
  txs: List[Tx0]) extends Message {
  type E = Block
  def companion = Block
}

object Block extends MessageCompanion[Block] {
  def codec(version: Int) = {
    ("block_header" | Codec[BlockHeader]) ::
      ("txs" | VarList.varList(Tx0.codec(version)))
  }.as[Block]
  def command = "block"
}

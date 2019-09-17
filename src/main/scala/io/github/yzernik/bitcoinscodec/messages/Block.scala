package io.github.yzernik.bitcoinscodec.messages

import io.github.yzernik.bitcoinscodec.structures.{BlockHeader, Hash, Hashable, Message, MessageCompanion, VarList}
import scodec.Codec
import scodec.codecs._

case class Block(
  block_header: BlockHeader,
  txs: List[Tx]) extends Message with Hashable{
  type E = Block
  def companion = Block

  override def hash: Hash =
    block_header.hash
}

object Block extends MessageCompanion[Block] {
  override def codec(version: Int) = {
    ("block_header" | Codec[BlockHeader]) ::
      ("txs" | VarList(Tx.codec(version)))
  }.as[Block]
  override def command = "block"
}

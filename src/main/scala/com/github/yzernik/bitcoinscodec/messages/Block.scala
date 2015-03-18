package com.github.yzernik.bitcoinscodec.messages

import com.github.yzernik.bitcoinscodec.structures._
import com.github.yzernik.bitcoinscodec.structures.Message

import scodec.Codec
import scodec.codecs._

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

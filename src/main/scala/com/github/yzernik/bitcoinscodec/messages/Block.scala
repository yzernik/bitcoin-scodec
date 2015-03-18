package com.github.yzernik.bitcoinscodec.messages

import com.github.yzernik.bitcoinscodec.structures.BlockHeader
import com.github.yzernik.bitcoinscodec.structures.Message
import com.github.yzernik.bitcoinscodec.structures.MessageCompanion
import com.github.yzernik.bitcoinscodec.structures.VarList

import scodec.Codec
import scodec.ValueCodecEnrichedWithHListSupport
import scodec.codecs.StringEnrichedWithCodecNamingSupport

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

package com.oohish.bitcoinscodec.messages

import com.oohish.bitcoinscodec.structures._
import com.oohish.bitcoinscodec.structures.Message._

import scodec.Codec
import scodec.codecs._

case class Block(
  block_header: BlockHeader,
  txs: List[Tx]) extends Message {
  type E = Block
  def codec = Block.codec
}

object Block {
  import VarList._

  implicit val codec = {
    ("block_header" | Codec[BlockHeader]) ::
      ("txs" | VarList.varList(Codec[Tx]))
  }.as[Block]

}
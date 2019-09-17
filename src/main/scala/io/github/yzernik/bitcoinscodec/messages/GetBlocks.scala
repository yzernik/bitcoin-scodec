package io.github.yzernik.bitcoinscodec.messages

import io.github.yzernik.bitcoinscodec.structures.Hash
import io.github.yzernik.bitcoinscodec.structures.Message
import io.github.yzernik.bitcoinscodec.structures.MessageCompanion
import io.github.yzernik.bitcoinscodec.structures.VarList

import scodec.Codec
import scodec.bits.ByteVector
import scodec.codecs._

case class GetBlocks(
  version: Long,
  block_locator_hashes: List[Hash],
  hash_stop: Hash = Hash.NULL) extends Message {
  type E = GetBlocks
  def companion = GetBlocks
}

object GetBlocks extends MessageCompanion[GetBlocks] {
  def codec(version: Int): Codec[GetBlocks] = {
    ("version" | uint32L) ::
      ("block_locator_hashes" | VarList(Codec[Hash])) ::
      ("hash_stop" | Codec[Hash])
  }.as[GetBlocks]
  def command = "getblocks"
}

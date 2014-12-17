package com.github.yzernik.bitcoinscodec.messages

import scodec.bits.ByteVector
import scodec.Codec
import scodec.codecs
import scalaz.std.anyVal.unitInstance
import scodec.bits.BitVector
import scodec.Codec
import scodec.codecs._
import shapeless._
import com.github.yzernik.bitcoinscodec.structures.VarList
import com.github.yzernik.bitcoinscodec.structures.Message
import com.github.yzernik.bitcoinscodec.structures.InvVect
import com.github.yzernik.bitcoinscodec.structures.Hash
import com.github.yzernik.bitcoinscodec.structures.VarInt
import com.github.yzernik.bitcoinscodec.structures.MessageCompanion

case class GetBlocks(
  version: Long,
  block_locator_hashes: List[Hash],
  hash_stop: Hash = GetBlocks.zeroStop) extends Message {
  type E = GetBlocks
  def companion = GetBlocks
}

object GetBlocks extends MessageCompanion[GetBlocks] {
  val zeroStop = Hash(ByteVector.fill(32)(0))
  def codec(version: Int): Codec[GetBlocks] = {
    ("version" | uint32L) ::
      ("block_locator_hashes" | VarList.varList(Codec[Hash])) ::
      ("hash_stop" | Codec[Hash])
  }.as[GetBlocks]
  def command = "getblocks"
}
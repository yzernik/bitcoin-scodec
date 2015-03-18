package com.github.yzernik.bitcoinscodec.messages

import com.github.yzernik.bitcoinscodec.structures.Hash
import com.github.yzernik.bitcoinscodec.structures.Message
import com.github.yzernik.bitcoinscodec.structures.MessageCompanion
import com.github.yzernik.bitcoinscodec.structures.VarList

import scodec.Codec
import scodec.HListCodecEnrichedWithHListSupport
import scodec.ValueCodecEnrichedWithHListSupport
import scodec.bits.ByteVector
import scodec.codecs.StringEnrichedWithCodecNamingSupport
import scodec.codecs.uint32L

case class GetHeaders(
  version: Long,
  block_locator_hashes: List[Hash],
  hash_stop: Hash = GetBlocks.zeroStop) extends Message {
  type E = GetHeaders
  def companion = GetHeaders
}

object GetHeaders extends MessageCompanion[GetHeaders] {
  val zeroStop = Hash(ByteVector.fill(32)(0))
  def codec(version: Int): Codec[GetHeaders] = {
    ("version" | uint32L) ::
      ("block_locator_hashes" | VarList.varList(Codec[Hash])) ::
      ("hash_stop" | Codec[Hash])
  }.as[GetHeaders]

  def command = "getheaders"
}

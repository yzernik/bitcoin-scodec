package com.oohish.bitcoinscodec.messages

import scodec.bits.ByteVector
import scodec.Codec
import scodec.codecs
import scalaz.std.anyVal.unitInstance
import scodec.bits.BitVector
import scodec.Codec
import scodec.codecs._
import shapeless._
import com.oohish.bitcoinscodec.structures.VarList
import com.oohish.bitcoinscodec.structures.Message.Message
import com.oohish.bitcoinscodec.structures.InvVect
import com.oohish.bitcoinscodec.structures.Hash
import com.oohish.bitcoinscodec.structures.VarInt

case class GetBlocks(
  version: Long,
  hash_count: Long,
  block_locator_hashes: List[Hash],
  hash_stop: Hash) extends Message {
  type E = GetBlocks
  def codec = GetBlocks.codec
}

object GetBlocks {
  import VarList._

  implicit val codec: Codec[GetBlocks] = {
    ("version" | uint32L) ::
      ("hash_count" | VarInt.varIntCodec) ::
      ("block_locator_hashes" | VarList.varList(Codec[Hash])) ::
      ("hash_stop" | Codec[Hash])
  }.as[GetBlocks]

}
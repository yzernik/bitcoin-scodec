package lktk.bmsg.messages

import lktk.bmsg.structures.{Hash, Message, MessageCompanion, VarList}

import scodec.Codec
import scodec.bits.ByteVector
import scodec.codecs._

case class GetBlocks(
  version: Long,
  blockLocatorHashes: List[Hash],
  hashStop: Hash = GetBlocks.zeroStop) extends Message {
  type E = GetBlocks
  def companion = GetBlocks
}

object GetBlocks extends MessageCompanion[GetBlocks] {
  val zeroStop = Hash(ByteVector.fill(32)(0))
  def codec(version: Int): Codec[GetBlocks] = {
    ("version" | uint32L) ::
      ("blockLocatorHashes" | VarList.varList(Codec[Hash])) ::
      ("hashStop" | Codec[Hash])
  }.as[GetBlocks]
  def command = "getblocks"
}

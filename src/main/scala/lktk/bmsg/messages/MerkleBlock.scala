package lktk.bmsg.messages

import lktk.bmsg.structures._

import scodec.Codec
import scodec.codecs._

case class MerkleBlock(
  blockHeader: BlockHeader,
  totalTransactions: Long,
  hashes: List[Hash],
  flags: List[Boolean]
) extends Message {
  type E = MerkleBlock
  def companion = MerkleBlock
}

object MerkleBlock extends MessageCompanion[MerkleBlock] {

  val flag: Codec[Boolean] = {
    ("flag" | mappedEnum(uint8, false -> 0, true -> 1))
  }.as[Boolean]

  def codec(version: Int): Codec[MerkleBlock] = (
    ("blockHeader" | BlockHeader.codec) ::
      ("totalTransactions" | uint32L) ::
      ("hashes" | VarList.varList(Hash.codec)) ::
      ("bits" | VarList.varList(flag))
    ).as[MerkleBlock]

  def command = "merkleblock"
}
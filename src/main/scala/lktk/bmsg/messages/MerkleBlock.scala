package lktk.bmsg.messages

import lktk.bmsg.structures._

import scodec.Codec
import scodec.codecs._

case class MerkleBlock(
  blockHeader: BlockHeader,
  partialMerkleTree: PartialMerkleTree
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
      ("partialMerkleTree" | PartialMerkleTree.codec)
    ).as[MerkleBlock]

  def command = "merkleblock"
}
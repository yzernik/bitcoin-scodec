package lktk.bmsg.structures

import scodec.Codec
import scodec.codecs._

case class PartialMerkleTree(
  totalTransactions: Long,
  hashes: List[Hash],
  flags: List[Boolean]
)

object PartialMerkleTree {

  val flag: Codec[Boolean] = {
    ("flag" | mappedEnum(uint8, false -> 0, true -> 1))
  }.as[Boolean]

  val codec: Codec[PartialMerkleTree] = (
      ("totalTransactions" | uint32L) ::
      ("hashes" | VarList.varList(Hash.codec)) ::
      ("flags" | VarList.varList(flag))
    ).as[PartialMerkleTree]
}
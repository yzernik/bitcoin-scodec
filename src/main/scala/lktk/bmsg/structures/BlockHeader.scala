package lktk.bmsg.structures

import scodec.Codec
import scodec.codecs._

case class BlockHeader(
  version: Long,
  prevBlock: Hash,
  merkleRoot: Hash,
  timestamp: Long,
  bits: Long,
  nonce: Long
)

object BlockHeader {

  implicit val codec: Codec[BlockHeader] = {
    ("version" | uint32L) ::
      ("prevBlock" | Codec[Hash]) ::
      ("merkleRoot" | Codec[Hash]) ::
      ("timestamp" | uint32L) ::
      ("bits" | uint32L) ::
      ("nonce" | uint32L)
  }.as[BlockHeader]

}

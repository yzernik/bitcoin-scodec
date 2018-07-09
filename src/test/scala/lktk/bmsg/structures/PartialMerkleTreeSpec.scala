package lktk.bmsg.structures

import lktk.bmsg.CodecSuite
import lktk.bmsg.util.{BitcoinCashParams, ProtocolVersion}
import scodec.bits._

class PartialMerkleTreeSpec extends CodecSuite {

  val hashes = List(
    Hash(hex"0000000000000000000000000000000000000000000000000000000000000000"),
    Hash(hex"4a5e1e4baab89f3a32518a88c31bc87f618f76673e2cc77ab2127b7afdeda33b")
  )

  "MerkleBlock codec" should {
    "roundtrip" in {
      val merkleBlock = PartialMerkleTree(2, hashes, List(true,false))

      roundtrip(PartialMerkleTree.codec, merkleBlock)
    }
  }
}

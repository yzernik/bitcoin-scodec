package lktk.bmsg.messages

import lktk.bmsg.CodecSuite
import lktk.bmsg.structures.{BlockHeader, Hash, Message, PartialMerkleTree}
import lktk.bmsg.util.{BitcoinCashParams, ProtocolVersion}
import scodec.bits._

class MerkleBlockSpec extends CodecSuite {

  val blockHeader = BlockHeader(
    1,
    Hash(hex"0000000000000000000000000000000000000000000000000000000000000000"),
    Hash(hex"4a5e1e4baab89f3a32518a88c31bc87f618f76673e2cc77ab2127b7afdeda33b"),
    1231006505L,
    486604799L,
    2083236893L)

  val partialMerkleTree = PartialMerkleTree(
    totalTransactions = 2,
    hashes = List(
      Hash(hex"0000000000000000000000000000000000000000000000000000000000000000"),
      Hash(hex"4a5e1e4baab89f3a32518a88c31bc87f618f76673e2cc77ab2127b7afdeda33b")
    ),
    flags = List(true,false)
  )

  "PartialMerkleTree codec" should {
    "roundtrip" in {
      val merkleBlock = MerkleBlock(blockHeader, partialMerkleTree)

      roundtrip(MerkleBlock.codec(ProtocolVersion.bloomVersion), merkleBlock)
      roundtrip(Message.codec(BitcoinCashParams.testnet, ProtocolVersion.bloomVersion), merkleBlock)
    }
  }
}

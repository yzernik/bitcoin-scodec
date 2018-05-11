package lktk.bmsg.messages

import lktk.bmsg.CodecSuite
import lktk.bmsg.structures.{BlockHeader, Hash, Message}
import lktk.bmsg.util._
import scodec.bits._

class CmpctBlockSpec extends CodecSuite {
  "CmpctBlock codec" should {
    "roundtrip" in {

      val blkHeader = BlockHeader(
        1L,
        Hash(hex"0000000000000000000000000000000000000000000000000000000000000000"),
        Hash(hex"4a5e1e4baab89f3a32518a88c31bc87f618f76673e2cc77ab2127b7afdeda33b"),
        1231006505L,
        486604799L,
        2083236893L
      )

      val cmpctBlock = CmpctBlock(blkHeader, 0, Nil, Nil)

      roundtrip(CmpctBlock.codec(ProtocolVersion.SHORT_IDS_BLOCKS_VERSION), cmpctBlock)
      roundtrip(Message.codec(BitcoinCashParams.testnet, ProtocolVersion.SHORT_IDS_BLOCKS_VERSION), cmpctBlock)
    }
  }
}

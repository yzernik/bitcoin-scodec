package lktk.bmsg.messages

import lktk.bmsg.CodecSuite
import lktk.bmsg.structures.{Hash, BlockTxn, Message}
import lktk.bmsg.util._
class BlockTxnSpec extends CodecSuite {
  "BlockTxn codec" should {
    "roundtrip" in {

      val tx1 = Tx(1L, List(), List(), 12345L)
      val txns = List.fill(4)(tx1)
      val blocktxn = BlockTxn(Hash.NULL, txns)

      roundtrip(BlockTxn.codec(ProtocolVersion.SHORT_IDS_BLOCKS_VERSION), blocktxn)
      roundtrip(Message.codec(BitcoinCashParams.testnet, ProtocolVersion.SHORT_IDS_BLOCKS_VERSION), blocktxn)
    }
  }
}

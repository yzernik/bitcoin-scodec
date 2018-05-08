package lktk.bmsg.messages

import lktk.bmsg.CodecSuite
import lktk.bmsg.structures.{BlockHeader, Hash, Message}
import lktk.bmsg.util._
import scodec.bits._

class GetBlockTxnSpec extends CodecSuite {
  "GetBlockTxn codec" should {
    "roundtrip" in {

      val hash = Hash(hex"4a5e1e4baab89f3a32518a88c31bc87f618f76673e2cc77ab2127b7afdeda33b")

      val getblkTxn = GetBlockTxn(hash, List(1,2,3,4))

      roundtrip(GetBlockTxn.codec(ProtocolVersion.v70014), getblkTxn)
      roundtrip(Message.codec(BitcoinCashParams.testnet, ProtocolVersion.v70014), getblkTxn)
    }
  }
}

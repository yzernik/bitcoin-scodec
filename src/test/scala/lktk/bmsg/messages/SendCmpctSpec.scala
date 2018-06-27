package lktk.bmsg.messages

import lktk.bmsg.CodecSuite
import lktk.bmsg.structures.Message
import lktk.bmsg.util._

class SendCmpctSpec extends CodecSuite {
  "SendCmpctSpec codec" should {
    "roundtrip" in {
      val announce = SendCmpct(true, 1)
      val notAnnounce = SendCmpct(false, 1)

      roundtrip(SendCmpct.codec(1), announce)
      roundtrip(SendCmpct.codec(1), notAnnounce)
      roundtrip(Message.codec(BitcoinCashParams.testnet, ProtocolVersion.shortIdsBlocksVersion), announce)
    }
  }
}

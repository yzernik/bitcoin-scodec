package lktk.bmsg.messages

import lktk.bmsg.CodecSuite
import lktk.bmsg.structures.Message
import lktk.bmsg.util.{BitcoinCashParams, ProtocolVersion}

class FilterClearSpec extends CodecSuite {

  "FilterClear codec" should {
    "roundtrip" in {
      val filterclear = FilterClear()
      roundtrip(FilterClear.codec(ProtocolVersion.bloomVersion), filterclear)
      roundtrip(Message.codec(BitcoinCashParams.testnet, ProtocolVersion.bloomVersion), filterclear)
    }
  }
}

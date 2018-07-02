package lktk.bmsg.messages

import lktk.bmsg.CodecSuite
import lktk.bmsg.structures.Message

class PingSpec extends CodecSuite {

  "Ping codec" should {
    "roundtrip" in {
      val ping = Ping(BigInt(0))
      roundtrip(Ping.codec(1), ping)
      roundtrip(Ping.codec(1), Ping.generate)
      roundtrip(Message.codec(0xDAB5BFFAL, 1), ping)
      roundtrip(Ping.codec(1), Ping(BigInt(1234)))
      roundtrip(Ping.codec(1), Ping(BigInt(Long.MaxValue)))
      roundtrip(Ping.codec(1), Ping(BigInt(Long.MaxValue) * 2 + 1))
    }
  }
}

package lktk.bmsg.messages

import lktk.bmsg.CodecSuite
import lktk.bmsg.structures.{Ping, TypeMessage, TypeMsg}
import lktk.bmsg.messages.PingCodecs._

class PingSpec extends CodecSuite {

  "Ping codec" should {
    "roundtrip" in {
      val ping = Ping(BigInt(0))
      roundtrip(ping)
      roundtrip(PingCodecs.generate)
      roundtrip(TypeMessage.codec(0xDAB5BFFAL, 1), ping)
      roundtrip(Ping(BigInt(1234)))
      roundtrip(Ping(BigInt(Long.MaxValue)))
      roundtrip(Ping(BigInt(Long.MaxValue) * 2 + 1))
    }
  }
}

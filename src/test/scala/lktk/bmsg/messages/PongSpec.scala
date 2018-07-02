package lktk.bmsg.messages

import lktk.bmsg.CodecSuite
import lktk.bmsg.structures.Message

class PongSpec extends CodecSuite {

  "Pong codec" should {
    "roundtrip" in {
      val pong = Pong(BigInt(0))
      roundtrip(Pong.codec(1), pong)
      roundtrip(Pong.codec(1), Pong.generate)
      roundtrip(Message.codec(0xDAB5BFFAL, 1), pong)
      roundtrip(Pong.codec(1), Pong(BigInt(1234)))
      roundtrip(Pong.codec(1), Pong(BigInt(Long.MaxValue)))
      roundtrip(Pong.codec(1), Pong(BigInt(Long.MaxValue) * 2 + 1))
    }
  }
}

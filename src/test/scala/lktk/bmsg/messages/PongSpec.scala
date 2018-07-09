package lktk.bmsg.messages

import lktk.bmsg.CodecSuite
import lktk.bmsg.structures.{Pong, TypeMessage}
import lktk.bmsg.messages.PongCodecs._

class PongSpec extends CodecSuite {

  "Pong codec" should {
    "roundtrip" in {
      val pong = Pong(BigInt(0))
      roundtrip(pong)
      roundtrip(PongCodecs.generate)
      roundtrip(TypeMessage.codec(0xDAB5BFFAL, 1), pong)
      roundtrip(Pong(BigInt(1234)))
      roundtrip(Pong(BigInt(Long.MaxValue)))
      roundtrip(Pong(BigInt(Long.MaxValue) * 2 + 1))
    }
  }
}

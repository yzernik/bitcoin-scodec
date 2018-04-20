package lktk.bp2p.messages

import lktk.bchmsg.CodecSuite
import lktk.bchmsg.messages.Pong
import lktk.bchmsg.structures.Message

import lktk.bchmsg.messages._

class PongSpec extends CodecSuite {

  "Pong codec" should {
    "roundtrip" in {
      val pong = Pong(BigInt(0))
      roundtrip(Pong.codec(1), pong)
      roundtrip(Message.codec(0xDAB5BFFAL, 1), pong)
      roundtrip(Pong.codec(1), Pong(BigInt(1234)))
      roundtrip(Pong.codec(1), Pong(BigInt(Long.MaxValue)))
      roundtrip(Pong.codec(1), Pong(BigInt(Long.MaxValue) * 2 + 1))
    }
  }
}

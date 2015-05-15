package io.github.yzernik.bitcoinscodec.messages

import io.github.yzernik.bitcoinscodec.CodecSuite
import io.github.yzernik.bitcoinscodec.structures._

class PongSpec extends CodecSuite {

  import Pong._

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

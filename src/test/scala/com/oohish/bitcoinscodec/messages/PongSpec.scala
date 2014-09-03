package com.oohish.bitcoinscodec.messages

import com.oohish.bitcoinscodec.CodecSuite

class PongSpec extends CodecSuite {

  import Pong._

  "Pong codec" should {
    "roundtrip" in {
      roundtrip(Pong(BigInt(0)))
      roundtrip(Pong(BigInt(1234)))
      roundtrip(Pong(BigInt(Long.MaxValue)))
      roundtrip(Pong(BigInt(Long.MaxValue) * 2 + 1))
    }
  }
}

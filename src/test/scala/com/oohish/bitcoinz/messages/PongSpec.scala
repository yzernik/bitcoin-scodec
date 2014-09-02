package com.oohish.bitcoinz.messages

import com.oohish.bitcoinz.CodecSuite

class PongSpec extends CodecSuite {

  import Pong._

  "Pong codec" should {
    "roundtrip" in {
      roundtrip(Pong(1234))
      roundtrip(Pong(Long.MinValue))
      roundtrip(Pong(Long.MaxValue))
    }
  }
}

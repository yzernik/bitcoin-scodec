package com.oohish.bitcoinscodec.messages

import com.oohish.bitcoinscodec.CodecSuite

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

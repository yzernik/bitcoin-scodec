package com.oohish.bitcoinscodec.messages

import com.oohish.bitcoinscodec.CodecSuite

class PingSpec extends CodecSuite {


import Ping._

  "Ping codec" should {
    "roundtrip" in {
      roundtrip(Ping(1234))
      roundtrip(Ping(Long.MinValue))
      roundtrip(Ping(Long.MaxValue))
    }
  }
}

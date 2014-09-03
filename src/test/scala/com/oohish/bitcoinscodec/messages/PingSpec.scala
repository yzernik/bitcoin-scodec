package com.oohish.bitcoinscodec.messages

import com.oohish.bitcoinscodec.CodecSuite

class PingSpec extends CodecSuite {

  import Ping._

  "Ping codec" should {
    "roundtrip" in {
      roundtrip(Ping(BigInt(0)))
      roundtrip(Ping(BigInt(1234)))
      roundtrip(Ping(BigInt(Long.MaxValue)))
      roundtrip(Ping(BigInt(Long.MaxValue) * 2 + 1))
    }
  }
}

package com.oohish.bitcoinscodec.messages

import com.oohish.bitcoinscodec.CodecSuite
import com.oohish.bitcoinscodec.structures._

class PingSpec extends CodecSuite {

  import Ping._

  "Ping codec" should {
    "roundtrip" in {
      val ping = Ping(BigInt(0))
      roundtrip(ping)
      roundtrip(Message.codec(0xDAB5BFFAL), ping)
      roundtrip(Ping(BigInt(1234)))
      roundtrip(Ping(BigInt(Long.MaxValue)))
      roundtrip(Ping(BigInt(Long.MaxValue) * 2 + 1))
    }
  }
}

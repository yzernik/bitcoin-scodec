package com.github.yzernik.bitcoinscodec.messages

import com.github.yzernik.bitcoinscodec.CodecSuite
import com.github.yzernik.bitcoinscodec.structures._

class PingSpec extends CodecSuite {

  import Ping._

  "Ping codec" should {
    "roundtrip" in {
      val ping = Ping(BigInt(0))
      roundtrip(Ping.codec(1), ping)
      roundtrip(Message.codec(0xDAB5BFFAL, 1), ping)
      roundtrip(Ping.codec(1), Ping(BigInt(1234)))
      roundtrip(Ping.codec(1), Ping(BigInt(Long.MaxValue)))
      roundtrip(Ping.codec(1), Ping(BigInt(Long.MaxValue) * 2 + 1))
    }
  }
}

package com.oohish.bitcoinscodec.messages

import com.oohish.bitcoinscodec.CodecSuite
import com.oohish.bitcoinscodec.structures._

class PongSpec extends CodecSuite {

  import Pong._

  "Pong codec" should {
    "roundtrip" in {
      val pong = Pong(BigInt(0))
      roundtrip(pong)
      roundtrip(Message.codec(0xDAB5BFFAL), pong)
      roundtrip(Pong(BigInt(1234)))
      roundtrip(Pong(BigInt(Long.MaxValue)))
      roundtrip(Pong(BigInt(Long.MaxValue) * 2 + 1))
    }
  }
}

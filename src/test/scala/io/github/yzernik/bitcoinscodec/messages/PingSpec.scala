package io.github.yzernik.bitcoinscodec.messages

import io.github.yzernik.bitcoinscodec.CodecSuite
import io.github.yzernik.bitcoinscodec.structures._
import scodec.bits.ByteVector

class PingSpec extends CodecSuite {

  import Ping._

  "Ping codec" should {
    "roundtrip" in {
      val ping = Ping(UInt64(0))
      roundtrip(Ping.codec(1), ping)
      roundtrip(Message.codec(0xDAB5BFFAL, 1), ping)
      roundtrip(Ping.codec(1), Ping(UInt64(1234)))
      roundtrip(Ping.codec(1), Ping(UInt64(Long.MaxValue)))

      val value = UInt64(ByteVector.fill(8)(0x42))
      roundtrip(Ping.codec(1), Ping(value))
    }
  }
}

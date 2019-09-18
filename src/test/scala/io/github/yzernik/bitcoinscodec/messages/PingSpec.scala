package io.github.yzernik.bitcoinscodec.messages

import io.github.yzernik.bitcoinscodec.CodecSuite
import io.github.yzernik.bitcoinscodec.structures._
import io.github.yzernik.bitcoinscodec.util.Util
import scodec.bits.{ByteVector, _}

class PingSpec extends CodecSuite {

  "Ping codec" should {
    "roundtrip" in {
      val ping = Ping(Util.generateNonce64)
      roundtrip(Ping.codec(1), ping)
      roundtrip(Message.codec(Message.TESTNET, 1), ping)
      roundtrip(Ping.codec(1), Ping(UInt64(1234L)))
      roundtrip(Ping.codec(1), Ping(UInt64(Long.MaxValue)))
      roundtrip(Ping.codec(1), Ping(UInt64(hex"ffffffffffffffff")))
      roundtrip(Ping.codec(1), Ping.generate)

      val value = UInt64(ByteVector.fill(8)(0x42))
      roundtrip(Ping.codec(1), Ping(value))
    }
  }
}

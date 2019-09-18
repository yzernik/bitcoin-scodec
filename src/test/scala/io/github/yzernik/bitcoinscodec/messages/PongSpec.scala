package io.github.yzernik.bitcoinscodec.messages

import io.github.yzernik.bitcoinscodec.CodecSuite
import io.github.yzernik.bitcoinscodec.structures._
import io.github.yzernik.bitcoinscodec.util.Util
import scodec.bits.ByteVector

class PongSpec extends CodecSuite {

  "Pong codec" should {
    "roundtrip" in {
      val pong = Pong(Util.generateNonce64)
      roundtrip(Pong.codec(1), pong)
      roundtrip(Message.codec(Message.TESTNET, 1), pong)
      roundtrip(Pong.codec(1), Pong(UInt64(1234L)))
      roundtrip(Pong.codec(1), Pong(UInt64(Long.MaxValue)))
      roundtrip(Pong.codec(1), Pong.generate)

      val value = UInt64(ByteVector.fill(8)(0x42))
      roundtrip(Pong.codec(1), Pong(value))
    }
  }
}

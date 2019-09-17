package io.github.yzernik.bitcoinscodec.messages

import io.github.yzernik.bitcoinscodec.CodecSuite
import io.github.yzernik.bitcoinscodec.structures._
import scodec.bits.ByteVector

class PongSpec extends CodecSuite {

  import Pong._

  "Pong codec" should {
    "roundtrip" in {
      val pong = Pong(UInt64(0))
      roundtrip(Pong.codec(1), pong)
      roundtrip(Message.codec(0xDAB5BFFAL, 1), pong)
      roundtrip(Pong.codec(1), Pong(UInt64(1234)))
      roundtrip(Pong.codec(1), Pong(UInt64(Long.MaxValue)))

      val value = UInt64(ByteVector.fill(8)(0x42))
      roundtrip(Pong.codec(1), Pong(value))
    }
  }
}

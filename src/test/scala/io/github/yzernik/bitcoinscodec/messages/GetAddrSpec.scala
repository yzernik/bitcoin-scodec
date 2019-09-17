package io.github.yzernik.bitcoinscodec.messages

import io.github.yzernik.bitcoinscodec.CodecSuite
import io.github.yzernik.bitcoinscodec.structures._

class GetAddrSpec extends CodecSuite {

  "GetAddr codec" should {
    "roundtrip" in {
      val getaddr = GetAddr()
      roundtrip(GetAddr.codec(1), getaddr)
      roundtrip(Message.codec(0xDAB5BFFAL, 1), getaddr)
    }
  }
}

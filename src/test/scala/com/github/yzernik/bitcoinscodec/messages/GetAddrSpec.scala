package com.github.yzernik.bitcoinscodec.messages

import com.github.yzernik.bitcoinscodec.CodecSuite
import com.github.yzernik.bitcoinscodec.structures._

class GetAddrSpec extends CodecSuite {

  import GetAddr._

  "GetAddr codec" should {
    "roundtrip" in {
      val getaddr = GetAddr()
      roundtrip(GetAddr.codec(1), getaddr)
      roundtrip(Message.codec(0xDAB5BFFAL, 1), getaddr)
    }
  }
}

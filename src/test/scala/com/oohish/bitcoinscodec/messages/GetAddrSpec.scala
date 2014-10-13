package com.oohish.bitcoinscodec.messages

import com.oohish.bitcoinscodec.CodecSuite
import com.oohish.bitcoinscodec.structures._

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

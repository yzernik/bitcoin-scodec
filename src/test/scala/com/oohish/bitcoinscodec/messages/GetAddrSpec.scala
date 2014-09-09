package com.oohish.bitcoinscodec.messages

import com.oohish.bitcoinscodec.CodecSuite

class GetAddrSpec extends CodecSuite {

  import GetAddr._

  "GetAddr codec" should {
    "roundtrip" in {
      roundtrip(GetAddr())
    }
  }
}

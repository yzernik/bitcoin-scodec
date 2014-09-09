package com.oohish.bitcoinscodec.messages

import com.oohish.bitcoinscodec.CodecSuite

class MemPoolSpec extends CodecSuite {

  import MemPool._

  "MemPool codec" should {
    "roundtrip" in {
      roundtrip(MemPool())
    }
  }
}

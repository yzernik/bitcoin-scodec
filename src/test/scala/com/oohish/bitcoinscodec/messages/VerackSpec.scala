package com.oohish.bitcoinscodec.messages

import com.oohish.bitcoinscodec.CodecSuite

class VerackSpec extends CodecSuite {


import Verack._

  "Verack codec" should {
    "roundtrip" in {
      roundtrip(Verack())
    }
  }
}

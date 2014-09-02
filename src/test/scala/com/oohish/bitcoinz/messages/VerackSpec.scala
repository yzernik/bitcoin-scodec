package com.oohish.bitcoinz.messages

import com.oohish.bitcoinz.CodecSuite

class VerackSpec extends CodecSuite {

  import Verack._

  "Verack codec" should {
    "roundtrip" in {
      roundtrip(Verack())
    }
  }
}

package com.oohish.bitcoinscodec.structures

import com.oohish.bitcoinscodec.CodecSuite

class VarStrSpec extends CodecSuite {

  import VarStr._

  "VarStr codec" should {

    "roundtrip" in {
      roundtrip("Hello")
      roundtrip("")
    }

  }
}

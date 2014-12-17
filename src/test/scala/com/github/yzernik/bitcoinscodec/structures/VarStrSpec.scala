package com.github.yzernik.bitcoinscodec.structures

import com.github.yzernik.bitcoinscodec.CodecSuite

class VarStrSpec extends CodecSuite {

  import VarStr._

  "VarStr codec" should {

    "roundtrip" in {
      roundtrip("Hello")
      roundtrip("")
    }

  }
}

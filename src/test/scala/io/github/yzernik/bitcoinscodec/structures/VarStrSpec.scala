package io.github.yzernik.bitcoinscodec.structures

import io.github.yzernik.bitcoinscodec.CodecSuite

class VarStrSpec extends CodecSuite {

  "VarStr codec" should {

    "roundtrip" in {
      roundtrip("Hello")(VarStr())
      roundtrip("")(VarStr())
    }

  }
}

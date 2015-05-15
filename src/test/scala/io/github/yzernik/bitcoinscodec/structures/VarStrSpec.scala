package io.github.yzernik.bitcoinscodec.structures

import io.github.yzernik.bitcoinscodec.CodecSuite

class VarStrSpec extends CodecSuite {

  import VarStr._

  "VarStr codec" should {

    "roundtrip" in {
      roundtrip("Hello")
      roundtrip("")
    }

  }
}

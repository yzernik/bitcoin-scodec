package lktk.bmsg.structures

import lktk.bmsg.CodecSuite

class VarStrSpec extends CodecSuite {

  "VarStr codec" should {

    "roundtrip" in {
      roundtrip(VarStr.codec, "Hello")
      roundtrip(VarStr.codec, "")
    }

  }
}

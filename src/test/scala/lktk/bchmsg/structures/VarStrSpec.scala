package lktk.bchmsg.structures

import lktk.bchmsg.CodecSuite

class VarStrSpec extends CodecSuite {

  import lktk.bchmsg.structures.VarStr._

  "VarStr codec" should {

    "roundtrip" in {
      roundtrip("Hello")
      roundtrip("")
    }

  }
}

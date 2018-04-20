package lktk.bchmsg.structures

import lktk.bchmsg.CodecSuite

import scodec.codecs.implicits._

class VarStrSpec extends CodecSuite {

  "VarStr codec" should {

    "roundtrip" in {
      roundtrip("Hello")
      roundtrip("")
    }

  }
}

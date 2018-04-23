package lktk.bmsg.structures

import lktk.bmsg.CodecSuite

import scodec.codecs.implicits._

class VarStrSpec extends CodecSuite {

  "VarStr codec" should {

    "roundtrip" in {
      roundtrip("Hello")
      roundtrip("")
    }

  }
}

package lktk.bmsg.messages

import lktk.bmsg.{CodecSuite, Generators}

class HeadersSpec extends CodecSuite {

  "Headers codec" should {
    "roundtrip" in {
      Generators.headerGen.map(h => roundtrip(Headers.codec(1), h)).sample
    }
  }

}
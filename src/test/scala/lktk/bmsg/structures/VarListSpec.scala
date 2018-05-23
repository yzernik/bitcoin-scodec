package lktk.bmsg.structures

import lktk.bmsg.CodecSuite
import lktk.bmsg.messages._

class VarListSpec extends CodecSuite {

  "VarList codec" should {

    implicit val codec = VarList.varList(Ping.codec(1))

    "roundtrip" in {
      roundtrip(codec, List())
      roundtrip(List(
        Ping(0),
        Ping(1),
        Ping(2)))
    }

  }
}

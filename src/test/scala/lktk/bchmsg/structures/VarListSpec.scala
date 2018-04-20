package lktk.bchmsg.structures

import lktk.bchmsg.CodecSuite
import lktk.bchmsg.messages._
import lktk.bchmsg.structures.VarList

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

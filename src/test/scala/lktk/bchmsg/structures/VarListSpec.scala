package lktk.bchmsg.structures

import lktk.bchmsg.CodecSuite
import lktk.bchmsg.messages.Ping

class VarListSpec extends CodecSuite {

  import lktk.bchmsg.structures.VarList._

  "VarList codec" should {

    implicit val codec = varList(Ping.codec(1))

    "roundtrip" in {
      roundtrip(codec, List())
      roundtrip(List(
        Ping(0),
        Ping(1),
        Ping(2)))
    }

  }
}

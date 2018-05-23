package lktk.bmsg.messages

import lktk.bmsg.structures.Message
import lktk.bmsg.CodecSuite

class VerackSpec extends CodecSuite {

  "Verack codec" should {
    "roundtrip" in {
      val verack = Verack()
      roundtrip(Verack.codec(1), verack)
      roundtrip(Message.codec(0xDAB5BFFAL, 1), verack)
    }
  }
}

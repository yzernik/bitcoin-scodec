package lktk.bmsg.messages

import lktk.bmsg.CodecSuite
import lktk.bmsg.messages.Verack
import lktk.bmsg.structures.Message
import lktk.bmsg.CodecSuite

import lktk.bmsg.messages._

class VerackSpec extends CodecSuite {

  "Verack codec" should {
    "roundtrip" in {
      val verack = Verack()
      roundtrip(Verack.codec(1), verack)
      roundtrip(Message.codec(0xDAB5BFFAL, 1), verack)
    }
  }
}

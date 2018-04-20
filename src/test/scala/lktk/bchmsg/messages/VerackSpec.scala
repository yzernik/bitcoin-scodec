package lktk.bchmsg.messages

import lktk.bchmsg.CodecSuite
import lktk.bchmsg.messages.Verack
import lktk.bchmsg.structures.Message
import lktk.bchmsg.CodecSuite

import lktk.bchmsg.messages._

class VerackSpec extends CodecSuite {

  "Verack codec" should {
    "roundtrip" in {
      val verack = Verack()
      roundtrip(Verack.codec(1), verack)
      roundtrip(Message.codec(0xDAB5BFFAL, 1), verack)
    }
  }
}

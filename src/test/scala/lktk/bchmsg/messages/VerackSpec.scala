package lktk.bchmsg.messages

import lktk.bchmsg.CodecSuite
import lktk.bchmsg.structures._
import lktk.bchmsg.messages.Verack
import lktk.bchmsg.structures.Message

class VerackSpec extends CodecSuite {

  import Verack._

  "Verack codec" should {
    "roundtrip" in {
      val verack = Verack()
      roundtrip(Verack.codec(1), verack)
      roundtrip(Message.codec(0xDAB5BFFAL, 1), verack)
    }
  }
}

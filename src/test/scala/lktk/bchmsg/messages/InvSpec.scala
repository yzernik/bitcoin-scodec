package lktk.bchmsg.messages

import lktk.bchmsg.CodecSuite
import lktk.bchmsg.structures._
import lktk.bchmsg.messages.Inv
import lktk.bchmsg.structures.{Hash, InvVect, Message}

class InvSpec extends CodecSuite {

  import Inv._

  val inv = Inv(List(InvVect(InvVect.MSG_TX,
    Hash(ByteVector.fill(32)(0x42)))))

  "Inv codec" should {
    "roundtrip" in {
      roundtrip(Inv.codec(1), inv)
      roundtrip(Message.codec(0xDAB5BFFAL, 1), inv)
    }

  }
}

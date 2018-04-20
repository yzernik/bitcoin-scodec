package lktk.bchmsg.messages

import lktk.bchmsg.CodecSuite
import lktk.bchmsg.structures._
import lktk.bchmsg.messages.NotFound
import lktk.bchmsg.structures.{Hash, InvVect, Message}

class NotFoundSpec extends CodecSuite {

  import NotFound._

  val notfound = NotFound(List(InvVect(InvVect.MSG_TX,
    Hash(ByteVector.fill(32)(0x42)))))
  "NotFound codec" should {
    "roundtrip" in {
      roundtrip(NotFound.codec(1), notfound)
      roundtrip(Message.codec(0xDAB5BFFAL, 1), notfound)
    }

  }
}

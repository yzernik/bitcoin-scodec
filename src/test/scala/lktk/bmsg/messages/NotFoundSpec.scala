package lktk.bmsg.messages

import lktk.bmsg.CodecSuite
import lktk.bmsg.structures.{Hash, InvVect, Message}

import scodec.bits.ByteVector

class NotFoundSpec extends CodecSuite {

  val notfound = NotFound(List(InvVect(InvVect.MSG_TX,
    Hash(ByteVector.fill(32)(0x42)))))
  "NotFound codec" should {
    "roundtrip" in {
      roundtrip(NotFound.codec(1), notfound)
      roundtrip(Message.codec(0xDAB5BFFAL, 1), notfound)
    }

  }
}

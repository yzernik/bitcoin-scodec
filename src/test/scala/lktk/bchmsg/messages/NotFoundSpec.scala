package lktk.bp2p.messages

import lktk.bchmsg.CodecSuite
import lktk.bchmsg.messages.NotFound
import lktk.bchmsg.structures.{Hash, InvVect, Message}

import lktk.bchmsg.messages._
import scodec.bits.ByteVector
import scodec.bits._
import scodec.codecs._

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

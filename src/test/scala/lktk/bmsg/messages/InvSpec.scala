package lktk.bmsg.messages

import lktk.bmsg.CodecSuite
import lktk.bmsg.messages.Inv
import lktk.bmsg.structures.InvVect.MSG_TX
import lktk.bmsg.structures.{Hash, InvVect, Message}
import lktk.bmsg.CodecSuite

import lktk.bmsg.messages._
import scodec.bits.ByteVector
import scodec.bits._
import scodec.codecs._

class InvSpec extends CodecSuite {

  val inv = Inv(List(InvVect(MSG_TX,
    Hash(ByteVector.fill(32)(0x42)))))

  "Inv codec" should {
    "roundtrip" in {
      roundtrip(Inv.codec(1), inv)
      roundtrip(Message.codec(0xDAB5BFFAL, 1), inv)
    }

  }
}

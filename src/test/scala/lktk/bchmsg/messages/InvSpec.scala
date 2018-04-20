package lktk.bchmsg.messages

import lktk.bchmsg.CodecSuite
import lktk.bchmsg.messages.Inv
import lktk.bchmsg.structures.InvVect.MSG_TX
import lktk.bchmsg.structures.{Hash, InvVect, Message}
import lktk.bchmsg.CodecSuite

import lktk.bchmsg.messages._
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

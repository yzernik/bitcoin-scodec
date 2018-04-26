package lktk.bp2p.messages

import lktk.bmsg.CodecSuite
import lktk.bmsg.messages.Reject

import lktk.bmsg.messages._
import lktk.bmsg.messages.Reject._
import lktk.bmsg.structures.Message
import scodec.bits.ByteVector

class RejectSpec extends CodecSuite {

  "Reject codec" should {
    "roundtrip" in {
      val reject = Reject(
        "Your message was rejected.",
        REJECT_MALFORMED,
        "Your message was malformed.")
      roundtrip(Reject.codec(1), Reject(
        "Your message was rejected.",
        REJECT_MALFORMED,
        "Your message was malformed."))
      roundtrip(Reject.codec(1), Reject(
        "Your message was rejected again.",
        REJECT_INVALID,
        "Your message was invalid."))
      roundtrip(Message.codec(0xDAB5BFFAL, 1), reject)
    }
  }
}
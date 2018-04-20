package lktk.bp2p.messages

import lktk.bchmsg.CodecSuite
import lktk.bchmsg.messages.Reject

import lktk.bchmsg.messages._
import lktk.bchmsg.messages.Reject._
import lktk.bchmsg.structures.Message
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

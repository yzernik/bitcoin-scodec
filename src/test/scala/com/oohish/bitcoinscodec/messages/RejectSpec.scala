package com.oohish.bitcoinscodec.messages

import com.oohish.bitcoinscodec.CodecSuite
import scodec.bits.ByteVector
import com.oohish.bitcoinscodec.structures._

class RejectSpec extends CodecSuite {

  import Reject._

  "Reject codec" should {
    "roundtrip" in {
      val reject = Reject(
        "Your message was rejected.",
        REJECT_MALFORMED,
        "Your message was malformed.")
      roundtrip(Reject(
        "Your message was rejected.",
        REJECT_MALFORMED,
        "Your message was malformed."))
      roundtrip(Reject(
        "Your message was rejected again.",
        REJECT_INVALID,
        "Your message was invalid."))
      roundtrip(Message.codec(0xDAB5BFFAL), reject)
    }
  }
}

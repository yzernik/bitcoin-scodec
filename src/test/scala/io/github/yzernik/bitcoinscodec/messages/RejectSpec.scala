package io.github.yzernik.bitcoinscodec.messages

import io.github.yzernik.bitcoinscodec.CodecSuite
import io.github.yzernik.bitcoinscodec.structures._

class RejectSpec extends CodecSuite {

  import Reject._

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
      roundtrip(Message.codec(Message.TESTNET, 1), reject)
    }
  }
}

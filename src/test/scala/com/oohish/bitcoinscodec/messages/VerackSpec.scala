package com.oohish.bitcoinscodec.messages

import com.oohish.bitcoinscodec.CodecSuite
import com.oohish.bitcoinscodec.structures._

class VerackSpec extends CodecSuite {

  import Verack._

  "Verack codec" should {
    "roundtrip" in {
      val verack = Verack()
      roundtrip(verack)
      roundtrip(Message.codec(0xDAB5BFFAL), verack)
    }
  }
}

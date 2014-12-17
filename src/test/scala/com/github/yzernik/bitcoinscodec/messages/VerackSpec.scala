package com.github.yzernik.bitcoinscodec.messages

import com.github.yzernik.bitcoinscodec.CodecSuite
import com.github.yzernik.bitcoinscodec.structures._

class VerackSpec extends CodecSuite {

  import Verack._

  "Verack codec" should {
    "roundtrip" in {
      val verack = Verack()
      roundtrip(Verack.codec(1), verack)
      roundtrip(Message.codec(0xDAB5BFFAL, 1), verack)
    }
  }
}

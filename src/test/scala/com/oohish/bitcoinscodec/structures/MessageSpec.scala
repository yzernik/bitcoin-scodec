package com.oohish.bitcoinscodec.structures

import scalaz.\/
import scodec.bits._

import com.oohish.bitcoinscodec.CodecSuite

class MessageSpec extends CodecSuite {

  import Message._
  import com.oohish.bitcoinscodec.messages._

  "Message codec" should {

    "roundtrip" in {
      val codec = Message.codec(0xD9B4BEF9L)
      roundtrip(codec, Verack())
    }

    "encode" in {
      val codec = Message.codec(0xD9B4BEF9L)
      codec.encode(Verack()) shouldBe
        \/.right(hex"F9 BE B4 D9 76 65 72 61  63 6B 00 00 00 00 00 00 00 00 00 00 5D F6 E0 E2".toBitVector)
    }

  }
}

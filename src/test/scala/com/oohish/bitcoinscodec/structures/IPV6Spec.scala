package com.oohish.bitcoinscodec.structures

import com.oohish.bitcoinscodec.CodecSuite
import scodec.bits._
import scodec.codecs._
import IPV6.codec

class IPV6Spec extends CodecSuite {

import IPV6._

  "IPV6 codec" should {
    "roundtrip" in {
      roundtrip(IPV6(hex"2001 0db8 85a3 0042 1000 8a2e 0370 7334"))
    }

    "print" in {
      IPV6(hex"2001 0db8 85a3 0042 1000 8a2e 0370 7334").toString shouldBe "2001:0db8:85a3:0042:1000:8a2e:0370:7334"
    }
  }
}

package com.oohish.bitcoinz.messages

import com.oohish.bitcoinz.CodecSuite
import scodec.bits.ByteVector

import scodec.bits._
import scodec.codecs._
import scalaz.\/

class IPV4Spec extends CodecSuite {

  import IPV4._

  "IPV4 codec" should {
    "roundtrip" in {
      roundtrip(IPV4(1234))
      roundtrip(Ping(Long.MinValue))
      roundtrip(Ping(Long.MaxValue))
    }

    "print" in {
      IPV4((10 << 24) + 1).toString shouldBe "10.0.0.1"
    }

    "encode" in {
      codec.encode(IPV4((10 << 24) + 1)) shouldBe
        \/.right(hex"00 00 00 00 00 00 00 00 00 00 FF FF 0A 00 00 01".toBitVector)
    }

    "decode" in {
      codec.decode(hex"00 00 00 00 00 00 00 00 00 00 FF FF 0A 00 00 01".toBitVector) shouldBe
        \/.right(BitVector.empty, IPV4((10 << 24) + 1))
    }
  }
}

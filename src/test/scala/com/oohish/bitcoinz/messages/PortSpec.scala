package com.oohish.bitcoinz.messages

import com.oohish.bitcoinz.CodecSuite
import scodec.bits.ByteVector

import scodec.bits._
import scodec.codecs._
import scalaz.\/

class PortSpec extends CodecSuite {

  import Port._

  "Port codec" should {
    "roundtrip" in {
      roundtrip(Port(1234))
    }

    "encode" in {
      codec.encode(Port(8333)) shouldBe
        \/.right(hex"20 8D".toBitVector)
    }

  }
}

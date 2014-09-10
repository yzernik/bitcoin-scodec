package com.oohish.bitcoinscodec.structures

import scalaz.\/

import com.oohish.bitcoinscodec.CodecSuite
import scodec.bits._
import scodec.codecs._
import com.oohish.bitcoinscodec.structures.Port.codec

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

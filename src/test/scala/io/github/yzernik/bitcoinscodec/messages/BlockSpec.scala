package io.github.yzernik.bitcoinscodec.messages

import io.github.yzernik.bitcoinscodec.CodecSuite

import scodec.bits.ByteVector
import scodec.bits._
import scodec.codecs._

class BlockSpec extends CodecSuite {

  import Block._

  "Block codec" should {
    "roundtrip" in {
      //roundtrip(block)
    }

  }
}

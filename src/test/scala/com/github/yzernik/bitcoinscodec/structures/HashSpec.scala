package com.github.yzernik.bitcoinscodec.structures

import com.github.yzernik.bitcoinscodec.CodecSuite
import scodec.bits.ByteVector

class HashSpec extends CodecSuite {

  import Hash._

  "Hash codec" should {
    "roundtrip" in {
      roundtrip(Hash(ByteVector.fill(32)(0x42)))
    }
  }
}

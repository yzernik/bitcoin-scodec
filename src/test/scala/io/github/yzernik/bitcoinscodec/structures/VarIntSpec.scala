package io.github.yzernik.bitcoinscodec.structures

import io.github.yzernik.bitcoinscodec.CodecSuite

class VarIntSpec extends CodecSuite {

  "VarInt codec" should {

    implicit val countCodec = VarInt.varIntCodec

    "roundtrip" in {
      roundtrip(0L)
      roundtrip(1L)
      roundtrip(2L)
      roundtrip(11L)
      roundtrip(111L)
      roundtrip(1111L)
      roundtrip(11111L)
      roundtrip(111111L)
      roundtrip(1111111L)
      roundtrip(11111111L)
      roundtrip(111111111L)
      roundtrip(1111111111L)
      roundtrip(11111111111L)
      roundtrip(111111111111L)
      roundtrip(1111111111111L)
    }

  }
}

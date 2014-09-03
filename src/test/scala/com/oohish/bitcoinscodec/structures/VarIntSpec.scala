package com.oohish.bitcoinscodec.structures

import com.oohish.bitcoinscodec.CodecSuite

class VarIntSpec extends CodecSuite {

  import VarInt._

  "VarInt codec" should {
    "roundtrip" in {
      roundtrip(VarInt(0))
      roundtrip(VarInt(1))
      roundtrip(VarInt(2))
      roundtrip(VarInt(11))
      roundtrip(VarInt(111))
      roundtrip(VarInt(1111))
      roundtrip(VarInt(11111))
      roundtrip(VarInt(111111))
      roundtrip(VarInt(1111111))
      roundtrip(VarInt(11111111))
      roundtrip(VarInt(111111111))
      roundtrip(VarInt(1111111111))
      roundtrip(VarInt(11111111111L))
      roundtrip(VarInt(111111111111L))
      roundtrip(VarInt(1111111111111L))
    }

  }
}

package lktk.bmsg.structures

import lktk.bmsg.CodecSuite

class VarIntSpec extends CodecSuite {

  "VarInt codec" should {

    implicit val countCodec = VarInt.varIntCodec.xmap(_.toInt, (i: Int) => i.toLong)

    "roundtrip" in {
      roundtrip(0)
      roundtrip(1)
      roundtrip(2)
      roundtrip(11)
      roundtrip(111)
      roundtrip(1111)
      roundtrip(11111)
      roundtrip(111111)
      roundtrip(1111111)
      roundtrip(11111111)
      roundtrip(111111111)
      roundtrip(1111111111)
      roundtrip(VarInt.varIntCodec, 11111111111L)
      roundtrip(VarInt.varIntCodec, 111111111111L)
      roundtrip(VarInt.varIntCodec, 1111111111111L)
    }

  }
}

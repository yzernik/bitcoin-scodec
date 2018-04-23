package lktk.bmsg.structures

import lktk.bmsg.CodecSuite
import lktk.bmsg.structures._

import scodec.bits.ByteVector

class HashSpec extends CodecSuite {

  "Hash codec" should {
    "roundtrip" in {
      roundtrip(Hash(ByteVector.fill(32)(0x42)))
    }
  }
}

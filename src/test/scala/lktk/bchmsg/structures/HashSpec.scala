package lktk.bchmsg.structures

import lktk.bchmsg.CodecSuite
import lktk.bchmsg.structures._

import scodec.bits.ByteVector

class HashSpec extends CodecSuite {

  "Hash codec" should {
    "roundtrip" in {
      roundtrip(Hash(ByteVector.fill(32)(0x42)))
    }
  }
}

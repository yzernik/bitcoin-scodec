package lktk.bmsg.structures

import lktk.bmsg.CodecSuite

import scodec.bits.ByteVector
import scodec.bits._

class HashSpec extends CodecSuite {

  "Hash codec" should {
    "roundtrip" in {
      roundtrip(Hash(ByteVector.fill(32)(0x42)))
    }

    "to string" in {
      val hsh = Hash(hex"000000000019d6689c085ae165831e934ff763ae46a2a6c172b3f1b60a8ce26f")
      hsh.toString shouldBe "000000000019d6689c085ae165831e934ff763ae46a2a6c172b3f1b60a8ce26f"
    }
  }
}

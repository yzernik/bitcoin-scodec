package lktk.bchmsg.structures

import lktk.bchmsg.CodecSuite
import lktk.bchmsg.structures.Hash

class HashSpec extends CodecSuite {

  import Hash._

  "Hash codec" should {
    "roundtrip" in {
      roundtrip(Hash(ByteVector.fill(32)(0x42)))
    }
  }
}

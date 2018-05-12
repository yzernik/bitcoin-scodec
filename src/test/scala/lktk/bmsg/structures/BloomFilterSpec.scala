package lktk.bmsg.structures

import lktk.bmsg.CodecSuite
import lktk.bmsg.util.ProtocolVersion

class BloomFilterSpec extends CodecSuite {

  "BloomFilter codec" should {
    "roundtrip" in {
      bloomfilterGen.map { fl =>
        roundtrip(BloomFilter.codec(ProtocolVersion.bloomVersion), fl)
      }.sample
    }
  }
}

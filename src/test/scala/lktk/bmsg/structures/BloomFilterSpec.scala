package lktk.bmsg.structures

import lktk.bmsg.{CodecSuite, Generators}
import lktk.bmsg.util.ProtocolVersion

class BloomFilterSpec extends CodecSuite {

  "BloomFilter codec" should {
    "roundtrip" in {
      Generators.bloomfilterGen.map { fl =>
        roundtrip(BloomFilter.codec(ProtocolVersion.bloomVersion), fl)
      }.sample
    }
  }
}

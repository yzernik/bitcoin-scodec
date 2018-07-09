package lktk.bmsg.structures

import lktk.bmsg.structures.BloomFilter.maxNHashFuncs
import lktk.bmsg.{CodecSuite, Generators}
import lktk.bmsg.util.ProtocolVersion

import scodec.bits._

class BloomFilterSpec extends CodecSuite {

  "BloomFilter codec" should {
    "roundtrip" in {
      Generators.bloomfilterGen.map { fl =>
        roundtrip(BloomFilter.codec(ProtocolVersion.bloomVersion), fl)
      }.sample
    }

    "too many hashfunc" in {
      val codec = BloomFilter.codec(ProtocolVersion.bloomVersion)
      val tooMany = hex"034242423c0000000c00000001"
      codec.decode(tooMany.toBitVector).toString shouldBe s"Failure(nHashFuncs: value above limit $maxNHashFuncs)"

    }
  }
}

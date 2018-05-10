package lktk.bp2p.messages

import lktk.bmsg.CodecSuite
import lktk.bmsg.structures.{BloomFilter, Message}
import lktk.bmsg.messages._
import lktk.bmsg.structures.BloomFilter._
import lktk.bmsg.util.{BitcoinCashParams, ProtocolVersion}
import scodec.bits.ByteVector
import org.scalacheck.Gen

class FilterLoadSpec extends CodecSuite {

  def filterLoadGen: Gen[FilterLoad] = for {
    bloomFilter <- bloomfilterGen
  } yield FilterLoad(bloomFilter)

  def bloomfilterGen: Gen[BloomFilter] = for {
    size <- Gen.choose(1, 36000)
    filter <- Gen.containerOfN[Seq, Byte](size, Gen.choose(0, 100).map(_.toByte))
    nHashFuncs <- Gen.choose[Long](1, 50)
    tweak <- Gen.choose[Long](1, Long.MaxValue)
    nFlags <- Gen.oneOf(BLOOM_UPDATE_NONE, BLOOM_UPDATE_ALL, BLOOM_UPDATE_P2PUBKEY_ONLY)
  } yield BloomFilter(ByteVector(filter), nHashFuncs, tweak, nFlags)

  "FilterLoad codec" should {
    "roundtrip" in {
      filterLoadGen.map { fl =>
        roundtrip(FilterLoad.codec(ProtocolVersion.v70001), fl)
        roundtrip(Message.codec(BitcoinCashParams.testnet, ProtocolVersion.v70001), fl)
      }
    }
  }
}

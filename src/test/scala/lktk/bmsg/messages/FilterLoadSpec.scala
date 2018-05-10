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


  "FilterLoad codec" should {
    "roundtrip" in {
      filterLoadGen.map { fl =>
        roundtrip(FilterLoad.codec(ProtocolVersion.v70001), fl)
        roundtrip(Message.codec(BitcoinCashParams.testnet, ProtocolVersion.v70001), fl)
      }.sample
    }
  }
}

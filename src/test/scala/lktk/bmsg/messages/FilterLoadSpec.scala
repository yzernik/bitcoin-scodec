package lktk.bmsg.messages

import lktk.bmsg.CodecSuite
import lktk.bmsg.Generators._
import lktk.bmsg.structures.Message
import lktk.bmsg.util.{BitcoinCashParams, ProtocolVersion}

import org.scalacheck.Gen

class FilterLoadSpec extends CodecSuite {

  def filterLoadGen: Gen[FilterLoad] = for {
    bloomFilter <- bloomfilterGen
  } yield FilterLoad(bloomFilter)

  "FilterLoad codec" should {
    "roundtrip" in {
      filterLoadGen.map { fl =>
        roundtrip(FilterLoad.codec(ProtocolVersion.bloomVersion), fl)
        roundtrip(Message.codec(BitcoinCashParams.testnet, ProtocolVersion.bloomVersion), fl)
      }.sample
    }
  }
}

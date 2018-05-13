package lktk.bmsg.messages

import lktk.bmsg.CodecSuite
import lktk.bmsg.structures._
import lktk.bmsg.util.BitcoinCashParams

import org.scalacheck.Gen

import scodec.bits._

class FeeFilterSpec extends CodecSuite {

  val protcolVersionBip133 = 700013

  "FeeFilter codec" should {
    "roundtrip" in {
      Gen.choose(0, Long.MaxValue).map { num =>
        val feefilter = FeeFilter(BigInt(num))
        roundtrip(FeeFilter.codec(protcolVersionBip133), feefilter)
        roundtrip(Message.codec(BitcoinCashParams.testnet, protcolVersionBip133), feefilter)
      }.sample
    }
    "decode" in {
      val expectedFeeFilter = FeeFilter(48508)
      val num = hex"7cbd000000000000"
      shouldDecodeFullyTo(FeeFilter.codec(protcolVersionBip133), num.toBitVector, expectedFeeFilter)
    }
  }
}

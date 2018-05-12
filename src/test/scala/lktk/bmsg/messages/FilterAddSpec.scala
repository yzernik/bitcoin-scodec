package lktk.bmsg.messages

import lktk.bmsg.CodecSuite
import lktk.bmsg.structures.Message
import lktk.bmsg.util.{BitcoinCashParams, ProtocolVersion}
import org.scalacheck.Gen
import scodec.bits.ByteVector

class FilterAddSpec extends CodecSuite {

  def filterLoadGen: Gen[FilterAdd] = for {
    size <- Gen.choose(1, 560)
    byteVector <- Gen.containerOfN[Seq, Byte](size, Gen.choose(0, 100).map(_.toByte))
  } yield FilterAdd(ByteVector(byteVector))


  "FilterAdd codec" should {
    "roundtrip" in {
      filterLoadGen.map { fl =>
        roundtrip(FilterAdd.codec(ProtocolVersion.bloomVersion), fl)
        roundtrip(Message.codec(BitcoinCashParams.testnet, ProtocolVersion.bloomVersion), fl)
      }.sample
    }
  }
}

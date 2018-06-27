package lktk.bmsg.structures

import lktk.bmsg.CodecSuite
import lktk.bmsg.structures.UInt64._
import scodec.Attempt.Failure
import spire.math.ULong
class UInt64Spec extends CodecSuite {

  "UInt64 codec" should {
    "roundtrip" in {
      roundtrip(UInt64.genRandom)
      roundtrip(BigInt(1234))
      roundtrip(BigInt(12345))
      roundtrip(BigInt(Long.MaxValue))
      roundtrip(ULong.MinValue.toBigInt)
      roundtrip(ULong.MaxValue.toBigInt)
      roundtrip(BigInt("18446744073709551615"))
    }

    "should fail" in {
      uint64L.encode(BigInt(-10)) shouldBe Failure(scodec.Err("cant encode negative number in uint64L: -10"))
    }
  }
}

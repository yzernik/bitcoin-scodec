package lktk.bmsg.structures

import lktk.bmsg.CodecSuite
import lktk.bmsg.structures._

class UInt64Spec extends CodecSuite {

  "UInt64 codec" should {
    "roundtrip" in {
      roundtrip(UInt64(UInt64.bigIntToLong(UInt64.genRandom)))
      roundtrip(UInt64(1234))
      roundtrip(UInt64(12345))
      roundtrip(UInt64(Long.MinValue))
      roundtrip(UInt64(Long.MaxValue))
    }

    "print" in {
      def shouldPrint(n: BigInt) = {
        UInt64(UInt64.bigIntToLong(n)).toString shouldBe n.toString
      }

      shouldPrint(0)
      shouldPrint(1234)
      shouldPrint(Long.MaxValue)
      shouldPrint(BigInt(Long.MaxValue) * 2 + 1)
    }
  }
}

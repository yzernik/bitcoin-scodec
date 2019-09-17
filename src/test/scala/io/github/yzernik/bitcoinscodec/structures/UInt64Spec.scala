package io.github.yzernik.bitcoinscodec.structures

import io.github.yzernik.bitcoinscodec.CodecSuite
import scodec.bits.ByteVector

class UInt64Spec extends CodecSuite {

  "UInt64 codec" should {
    "roundtrip" in {
      roundtrip(UInt64(1234))
      roundtrip(UInt64(12345))
      roundtrip(UInt64(0))
      roundtrip(UInt64(Long.MaxValue))
      roundtrip(UInt64(ByteVector.fill(8)(0x00)))
      roundtrip(UInt64(ByteVector.fill(8)(0x42)))
    }

    "print" in {
      def shouldPrint(n: UInt64) = {
        n.toString.length shouldBe 26
      }

      shouldPrint(UInt64(0))
      shouldPrint(UInt64(1234))
      shouldPrint(UInt64(Long.MaxValue))
      shouldPrint(UInt64(ByteVector.fill(8)(0x42)))
    }

  }
}

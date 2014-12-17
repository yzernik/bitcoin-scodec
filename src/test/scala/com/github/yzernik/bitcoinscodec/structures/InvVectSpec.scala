package com.github.yzernik.bitcoinscodec.structures

import com.github.yzernik.bitcoinscodec.CodecSuite
import scodec.bits.ByteVector

class InvVectSpec extends CodecSuite {

  import InvVect._

  "InvVect codec" should {
    "roundtrip" in {
      roundtrip(InvVect(ERROR, Hash(ByteVector.fill(32)(0x42))))
      roundtrip(InvVect(MSG_TX, Hash(ByteVector.fill(32)(0x42))))
      roundtrip(InvVect(MSG_BLOCK, Hash(ByteVector.fill(32)(0x43))))
    }
  }
}

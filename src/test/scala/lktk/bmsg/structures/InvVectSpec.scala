package lktk.bmsg.structures

import lktk.bmsg.CodecSuite
import lktk.bmsg.structures.InvVect._

import scodec.bits.ByteVector

class InvVectSpec extends CodecSuite {



  "InvVect codec" should {
    "roundtrip" in {
      roundtrip(InvVect(ERROR, Hash(ByteVector.fill(32)(0x42))))
      roundtrip(InvVect(MSG_TX, Hash(ByteVector.fill(32)(0x42))))
      roundtrip(InvVect(MSG_BLOCK, Hash(ByteVector.fill(32)(0x43))))
    }
  }
}

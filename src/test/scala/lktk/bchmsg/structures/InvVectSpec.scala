package lktk.bchmsg.structures

import lktk.bchmsg.CodecSuite
import lktk.bchmsg.structures.{Hash, InvVect}

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

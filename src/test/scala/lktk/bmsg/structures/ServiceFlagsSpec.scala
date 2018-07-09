package lktk.bmsg.structures

import lktk.bmsg.CodecSuite
import lktk.bmsg.structures.ServiceFlags._

import scodec.bits._

class ServiceFlagsSpec extends CodecSuite {
  "ServiceFlags codec" should {
    "validate" in {
      val service = NodeNetwork | NodeWitness

      (service & NodeNone) shouldBe 0
      (service & NodeNetwork) shouldBe 1
    }

    "decode" in {
      val service = NodeXThin | NodeBloom | NodeGetUTXO | NodeNetwork
      shouldDecodeFullyTo(serviceFlag, hex"0x1700000000000000".toBitVector, service)
    }
    "roundtrip" in {
      roundtrip(serviceFlag, NodeXThin | NodeBloom | NodeGetUTXO | NodeNetwork)
    }
  }
}

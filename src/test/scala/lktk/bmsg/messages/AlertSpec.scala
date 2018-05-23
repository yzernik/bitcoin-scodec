package lktk.bmsg.messages

import lktk.bmsg.CodecSuite

import scodec.bits._

class AlertSpec extends CodecSuite {

  val alrt = Alert(60000, 6L, 1L, 1, 1, List(1), 1, 1, List("test"), 1, "", "test", "")
  val bytes = hex"60ea0000060000000000000001000000000000000100000001000000010100000001000000010000000104746573740100000000047465737400"

  "Alert codec" should {
    "roundtrip" in {
      roundtrip(Alert.codec(1), alrt)
    }
    "decode" in {
      shouldDecodeFullyTo(Alert.codec(1), bytes.toBitVector, alrt)
    }
  }
}

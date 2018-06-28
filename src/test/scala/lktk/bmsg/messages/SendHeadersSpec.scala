package lktk.bmsg.messages

import lktk.bmsg.CodecSuite
import lktk.bmsg.structures.Message

class SendHeadersSpec extends CodecSuite {
  "SendHeaders codec" should {
    "roundtrip" in {
      val sendHeaders = SendHeaders()
      roundtrip(SendHeaders.codec(1), sendHeaders)
      roundtrip(Message.codec(0xDAB5BFFAL, 1), sendHeaders)
    }
  }
}

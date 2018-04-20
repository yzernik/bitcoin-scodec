package lktk.bchmsg.messages

import lktk.bchmsg.CodecSuite
import lktk.bchmsg.structures._
import lktk.bchmsg.messages.GetAddr
import lktk.bchmsg.structures.Message

class GetAddrSpec extends CodecSuite {

  import GetAddr._

  "GetAddr codec" should {
    "roundtrip" in {
      val getaddr = GetAddr()
      roundtrip(GetAddr.codec(1), getaddr)
      roundtrip(Message.codec(0xDAB5BFFAL, 1), getaddr)
    }
  }
}

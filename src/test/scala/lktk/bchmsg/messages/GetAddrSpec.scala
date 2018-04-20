package lktk.bp2p.messages

import lktk.bchmsg.CodecSuite
import lktk.bchmsg.messages.GetAddr
import lktk.bchmsg.structures.Message

import lktk.bchmsg.messages._

class GetAddrSpec extends CodecSuite {
  "GetAddr codec" should {
    "roundtrip" in {
      val getaddr = GetAddr()
      roundtrip(GetAddr.codec(1), getaddr)
      roundtrip(Message.codec(0xDAB5BFFAL, 1), getaddr)
    }
  }
}

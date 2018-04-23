package lktk.bp2p.messages

import lktk.bmsg.CodecSuite
import lktk.bmsg.messages.GetAddr
import lktk.bmsg.structures.Message

import lktk.bmsg.messages._

class GetAddrSpec extends CodecSuite {
  "GetAddr codec" should {
    "roundtrip" in {
      val getaddr = GetAddr()
      roundtrip(GetAddr.codec(1), getaddr)
      roundtrip(Message.codec(0xDAB5BFFAL, 1), getaddr)
    }
  }
}

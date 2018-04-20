package lktk.bchmsg.messages

import lktk.bchmsg.CodecSuite
import lktk.bchmsg.messages.GetData
import lktk.bchmsg.structures.InvVect.MSG_TX
import lktk.bchmsg.structures.{Hash, InvVect, Message}
import lktk.bchmsg.CodecSuite

import lktk.bchmsg.messages._
import scodec.bits.ByteVector
import scodec.bits._
import scodec.codecs._

class GetDataSpec extends CodecSuite {

  "GetData codec" should {
    "roundtrip" in {
      val getdata = GetData(List(InvVect(MSG_TX,
        Hash(ByteVector.fill(32)(0x42)))))
      roundtrip(GetData.codec(1), getdata)
      roundtrip(Message.codec(0xDAB5BFFAL, 1), getdata)
    }

  }
}

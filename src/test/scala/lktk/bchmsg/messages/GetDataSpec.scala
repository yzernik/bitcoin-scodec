package lktk.bchmsg.messages

import lktk.bchmsg.CodecSuite
import lktk.bchmsg.structures._
import lktk.bchmsg.messages.GetData
import lktk.bchmsg.structures.{Hash, InvVect, Message}

class GetDataSpec extends CodecSuite {

  import GetData._

  "GetData codec" should {
    "roundtrip" in {
      val getdata = GetData(List(InvVect(InvVect.MSG_TX,
        Hash(ByteVector.fill(32)(0x42)))))
      roundtrip(GetData.codec(1), getdata)
      roundtrip(Message.codec(0xDAB5BFFAL, 1), getdata)
    }

  }
}

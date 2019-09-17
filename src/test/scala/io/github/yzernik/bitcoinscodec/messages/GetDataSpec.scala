package io.github.yzernik.bitcoinscodec.messages

import io.github.yzernik.bitcoinscodec.CodecSuite
import scodec.bits.ByteVector
import scodec.bits._
import scodec.codecs._
import io.github.yzernik.bitcoinscodec.structures._

class GetDataSpec extends CodecSuite {

  "GetData codec" should {
    "roundtrip" in {
      val getdata = GetData(List(InvVect(InvVect.MSG_TX,
        Hash(ByteVector.fill(32)(0x42)))))
      roundtrip(GetData.codec(1), getdata)
      roundtrip(Message.codec(0xDAB5BFFAL, 1), getdata)
    }

  }
}

package com.github.yzernik.bitcoinscodec.messages

import com.github.yzernik.bitcoinscodec.CodecSuite
import scodec.bits.ByteVector
import scodec.bits._
import scodec.codecs._
import scalaz.\/
import com.github.yzernik.bitcoinscodec.structures._

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

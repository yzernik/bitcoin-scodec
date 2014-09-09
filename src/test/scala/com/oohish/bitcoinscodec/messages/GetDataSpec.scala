package com.oohish.bitcoinscodec.messages

import com.oohish.bitcoinscodec.CodecSuite
import scodec.bits.ByteVector
import scodec.bits._
import scodec.codecs._
import scalaz.\/
import com.oohish.bitcoinscodec.structures.InvVect
import com.oohish.bitcoinscodec.structures.Hash

class GetDataSpec extends CodecSuite {

  import GetData._

  val getdata = GetData(List(InvVect(InvVect.MSG_TX,
    Hash(ByteVector.fill(32)(0x42)))))

  "GetData codec" should {
    "roundtrip" in {
      roundtrip(getdata)
    }

  }
}

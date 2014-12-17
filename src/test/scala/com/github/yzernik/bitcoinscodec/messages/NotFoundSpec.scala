package com.github.yzernik.bitcoinscodec.messages

import com.github.yzernik.bitcoinscodec.CodecSuite
import scodec.bits.ByteVector
import scodec.bits._
import scodec.codecs._
import scalaz.\/
import com.github.yzernik.bitcoinscodec.structures._

class NotFoundSpec extends CodecSuite {

  import NotFound._

  val notfound = NotFound(List(InvVect(InvVect.MSG_TX,
    Hash(ByteVector.fill(32)(0x42)))))
  "NotFound codec" should {
    "roundtrip" in {
      roundtrip(NotFound.codec(1), notfound)
      roundtrip(Message.codec(0xDAB5BFFAL, 1), notfound)
    }

  }
}

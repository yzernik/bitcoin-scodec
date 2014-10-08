package com.oohish.bitcoinscodec.messages

import com.oohish.bitcoinscodec.CodecSuite
import scodec.bits.ByteVector
import scodec.bits._
import scodec.codecs._
import scalaz.\/
import com.oohish.bitcoinscodec.structures._

class HeadersSpec extends CodecSuite {

  import Headers._

  "Headers codec" should {
    "roundtrip" in {
      //roundtrip(headers)
    }

  }
}

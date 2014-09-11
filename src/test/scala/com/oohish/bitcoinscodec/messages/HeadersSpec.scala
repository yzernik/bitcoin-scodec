package com.oohish.bitcoinscodec.messages

import com.oohish.bitcoinscodec.CodecSuite
import scodec.bits.ByteVector
import scodec.bits._
import scodec.codecs._
import scalaz.\/
import com.oohish.bitcoinscodec.structures._

class HeadersSpec extends CodecSuite {

  import Headers._

  val header1 = BlockHeader(
    1L,
    Hash(ByteVector.fill(32)(0x42)),
    Hash(ByteVector.fill(32)(0x42)),
    1355854353L,
    1234L,
    212672)

  val headers = Headers(List(header1))

  "Headers codec" should {
    "roundtrip" in {
      roundtrip(headers)
      roundtrip(Message.codec(0xDAB5BFFAL), headers)
    }

  }
}

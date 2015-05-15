package io.github.yzernik.bitcoinscodec.messages

import io.github.yzernik.bitcoinscodec.CodecSuite
import scodec.bits.ByteVector
import scodec.bits._
import scodec.codecs._
import scalaz.\/
import io.github.yzernik.bitcoinscodec.structures._

class HeadersSpec extends CodecSuite {

  import Headers._

  "Headers codec" should {
    "roundtrip" in {
      //roundtrip(headers)
    }

    "decode" in {
      val buf = scalax.io.Resource.fromURL(getClass.getResource("/headerspayload.data"))
      val bytes = BitVector(buf.bytes)
      Headers.codec(1).decode(bytes).isRight shouldBe true
    }

  }
}

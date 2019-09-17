package io.github.yzernik.bitcoinscodec.messages

import io.github.yzernik.bitcoinscodec.CodecSuite
import scodec._
import scodec.bits._

class HeadersSpec extends CodecSuite {

  "Headers codec" should {
    "roundtrip" in {
      //roundtrip(headers)
    }

    "decode" in {
      val buf = scalax.io.Resource.fromURL(getClass.getResource("/headerspayload.data"))
      val bytes = BitVector(buf.bytes)

      val Attempt.Successful(DecodeResult(actual, rest)) = Headers.codec(1) decode bytes
      rest shouldBe BitVector.empty
    }

  }
}

package lktk.bchmsg.messages

import lktk.bchmsg.CodecSuite
import lktk.bchmsg.structures._
import lktk.bchmsg.messages.Headers

class HeadersSpec extends CodecSuite {

  import lktk.bchmsg.messages.Headers._

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

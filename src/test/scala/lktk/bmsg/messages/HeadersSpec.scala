package lktk.bmsg.messages

import lktk.bmsg.CodecSuite

class HeadersSpec extends CodecSuite {
  //TODO
  /*
  "Headers codec" should {
    "roundtrip" in {
      //roundtrip(headers)
    }

    "decode" in {
      val buf = scala.io.Source.fromURL(getClass.getResource("/headerspayload.data"))
      val bytes = BitVector(buf.toStream.map(_.toByte).toList)

      val Attempt.Successful(DecodeResult(actual, rest)) = Headers.codec(1) decode bytes
      rest shouldBe BitVector.empty
    }

  }
  */
}
package io.github.yzernik.bitcoinscodec.messages

import java.nio.file.{Files, Paths}

import io.github.yzernik.bitcoinscodec.CodecSuite
import scodec._
import scodec.bits._
import scodec.codecs._
import io.github.yzernik.bitcoinscodec.structures._

import scala.io.Source

class HeadersSpec extends CodecSuite {

  import Headers._

  "Headers codec" should {
    "roundtrip" in {
      //roundtrip(headers)
    }

    "decode" in {
      val resource = getClass.getResource("/headerspayload.data")
      val bytes = Files.readAllBytes(Paths.get(resource.getPath))
      val bitVector = BitVector(bytes)

      val Attempt.Successful(DecodeResult(actual, rest)) = Headers.codec(1) decode bitVector
      rest shouldBe BitVector.empty
    }

  }
}

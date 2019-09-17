package io.github.yzernik.bitcoinscodec.messages

import java.nio.file.{Files, Paths}

import io.github.yzernik.bitcoinscodec.CodecSuite
import scodec._
import scodec.bits._

import scala.io.Source

class HeadersSpec extends CodecSuite {

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

    "decode multiple messages" in {
      val resource = getClass.getResource("/headerspayload.data")
      val bytes = Files.readAllBytes(Paths.get(resource.getPath))
      val bitVector = BitVector(bytes)
      val combinedVector = bitVector ++ bitVector

      val Attempt.Successful(DecodeResult(actual, rest)) = Headers.codec(1) decode combinedVector
      rest shouldBe bitVector
    }

  }
}

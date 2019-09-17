package io.github.yzernik.bitcoinscodec.messages

import io.github.yzernik.bitcoinscodec.CodecSuite
import io.github.yzernik.bitcoinscodec.structures._
import scodec.bits._

class GetHeadersSpec extends CodecSuite {

  val getheaders = GetHeaders(
    70001L,
    List(Hash(hex"000000000019d6689c085ae165831e934ff763ae46a2a6c172b3f1b60a8ce26f")),
    Hash(hex"0000000000000000000000000000000000000000000000000000000000000000"))

  "GetHeaders codec" should {
    "roundtrip" in {
      roundtrip(GetHeaders.codec(1), getheaders)
      roundtrip(Message.codec(0xDAB5BFFAL, 1), getheaders)
    }

    "decode" in {
      val bytes = hex"""
      71110100
      01
      6fe28c0ab6f1b372c1a6a246ae63f74f931e8365e15a089c68d6190000000000
      0000000000000000000000000000000000000000000000000000000000000000
        """.toBitVector

      shouldDecodeFullyTo(GetHeaders.codec(1), bytes, getheaders)
    }

  }
}

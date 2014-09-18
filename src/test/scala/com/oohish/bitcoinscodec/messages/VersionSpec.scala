package com.oohish.bitcoinscodec.messages

import com.oohish.bitcoinscodec.CodecSuite
import scodec.bits.ByteVector
import scodec.bits._
import scodec.codecs._
import scalaz.\/
import com.oohish.bitcoinscodec.structures._

class VersionSpec extends CodecSuite {

  import Version._

  val version = Version(
    60002,
    1,
    1355854353L,
    NetworkAddress(1, Left(IPV4("0.0.0.0")), Port(0)),
    NetworkAddress(1, Left(IPV4("0.0.0.0")), Port(0)),
    7284544412836900411L,
    "/Satoshi:0.7.2/",
    212672,
    true)
  val bytes = hex"""
  	62 EA 00 00
  	01 00 00 00 00 00 00 00
  	11 B2 D0 50 00 00 00 00
  	01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 FF FF 00 00 00 00 00 00
  	01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 FF FF 00 00 00 00 00 00
  	3B 2E B3 5D 8C E6 17 65
  	0F 2F 53 61 74 6F 73 68 69 3A 30 2E 37 2E 32 2F
  	C0 3E 03 00  
    01
"""

  val messageBytes = hex"""
F9 BE B4 D9
76 65 72 73 69 6F 6E 00 00 00 00 00
65 00 00 00
3B 64 8D 5A
""" ++ bytes

  "Version codec" should {
    "roundtrip" in {
      roundtrip(codec, version)
      roundtrip(codec, version.copy(relay = false))
      roundtrip(Message.codec(0xDAB5BFFAL), version)
      roundtrip(Message.codec(0xD9B4BEF9L), version)
    }

    "encode" in {
      codec.encode(version) shouldBe
        \/.right(bytes.toBitVector)
    }

    "decode" in {
      codec.decode(bytes.toBitVector) shouldBe
        \/.right(BitVector.empty, version)
    }

  }
}

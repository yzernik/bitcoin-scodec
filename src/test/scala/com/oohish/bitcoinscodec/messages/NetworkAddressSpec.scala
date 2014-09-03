package com.oohish.bitcoinscodec.messages

import com.oohish.bitcoinscodec.CodecSuite

import scodec.bits.ByteVector
import scodec.bits._
import scodec.codecs._
import scalaz.\/

class NetworkAddressSpec extends CodecSuite {

  import NetworkAddress._

  "NetworkAddress codec" should {
    "roundtrip" in {
      roundtrip(NetworkAddress(1234, Left(IPV4("10.0.0.1")), Port(8080)))
      roundtrip(NetworkAddress(1234, Right(IPV6(hex"2001 0db8 85a3 0042 1000 8a2e 0370 7334")), Port(8080)))
      roundtrip(NetworkAddress(BigInt(Long.MaxValue) * 2 + 1, Left(IPV4(Int.MaxValue)), Port(65535)))
    }

    "encode" in {
      val services = 1L
      val ip = Left(IPV4("10.0.0.1"))
      val port = Port(8333)

      codec.encode(NetworkAddress(services, ip, port)) shouldBe
        \/.right(hex"01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 FF FF 0A 00 00 01 20 8D".toBitVector)
    }

    "decode" in {
      val services = 1L
      val ip = Left(IPV4("10.0.0.1"))
      val port = Port(8333)

      codec.decode(hex"01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 FF FF 0A 00 00 01 20 8D".toBitVector) shouldBe
        \/.right(BitVector.empty, NetworkAddress(services, ip, port))
    }

  }
}

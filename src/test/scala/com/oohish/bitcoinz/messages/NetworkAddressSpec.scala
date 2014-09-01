package com.oohish.bitcoinz.messages

import com.oohish.bitcoinz.CodecSuite
import scodec.bits.ByteVector

import scodec.bits._
import scodec.codecs._
import scalaz.\/

class NetworkAddressSpec extends CodecSuite {

  import NetworkAddress._

  "NetworkAddress codec" should {
    "roundtrip" in {
      roundtrip(NetworkAddress(1234, Left(IPV4(333)), Port(8080)))
      roundtrip(NetworkAddress(Long.MinValue, Left(IPV4(Int.MinValue)), Port(0)))
      roundtrip(NetworkAddress(Long.MaxValue, Left(IPV4(Int.MaxValue)), Port(65535)))
    }

    "encode" in {
      val services = 1L
      val ip = Left(IPV4((10 << 24) + 1))
      val port = Port(8333)

      codec.encode(NetworkAddress(services, ip, port)) shouldBe
        \/.right(hex"01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 FF FF 0A 00 00 01 20 8D".toBitVector)
    }

  }
}

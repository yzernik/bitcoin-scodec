package io.github.yzernik.bitcoinscodec.messages

import java.net.{InetAddress, InetSocketAddress}

import io.github.yzernik.bitcoinscodec.CodecSuite
import io.github.yzernik.bitcoinscodec.structures._
import io.github.yzernik.bitcoinscodec.util.Util
import scodec.bits._

class VersionSpec extends CodecSuite {

  val version = Version(
    60002,
    Version.NODE_NETWORK,
    1355854353L,
    NetworkAddress(UInt64(1), new InetSocketAddress(
      InetAddress.getByAddress(Array(0, 0, 0, 0).map(_.toByte)),
      0)),
    NetworkAddress(UInt64(1), new InetSocketAddress(
      InetAddress.getByAddress(Array(0, 0, 0, 0).map(_.toByte)),
      0)),
    UInt64(7284544412836900411L),
    "/Satoshi:0.7.2/",
    212672,
    true)

  val bytesV60002 = hex"""
  	62 EA 00 00
  	01 00 00 00 00 00 00 00
  	11 B2 D0 50 00 00 00 00
  	01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 FF FF 00 00 00 00 00 00
  	01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 FF FF 00 00 00 00 00 00
  	3B 2E B3 5D 8C E6 17 65
  	0F 2F 53 61 74 6F 73 68 69 3A 30 2E 37 2E 32 2F
  	C0 3E 03 00
"""

  val bytesV70001 = hex"""
    71 11 01 00
    01 00 00 00 00 00 00 00
    11 B2 D0 50 00 00 00 00
    01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 FF FF 00 00 00 00 00 00
    01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 FF FF 00 00 00 00 00 00
    3B 2E B3 5D 8C E6 17 65
    0F 2F 53 61 74 6F 73 68 69 3A 30 2E 37 2E 32 2F
    C0 3E 03 00
    01
"""

  "Version codec" should {
    "roundtrip" in {
      roundtrip(Version.codec(1), version)
      roundtrip(Version.codec(1), version.copy(version = 70001, relay = false))
      roundtrip(Version.codec(1), version.copy(version = 70001, relay = true))
      roundtrip(Version.codec(1), version.copy(nonce = Util.generateNonce64))
      roundtrip(Message.codec(Network.TestnetParams, 1), version)
      roundtrip(Message.codec(Network.MainnetParams, 1), version)
    }

    "decode" in {
      shouldDecodeFullyTo(Version.codec(1), bytesV60002.toBitVector, version)
      shouldDecodeFullyTo(Version.codec(1), bytesV70001.toBitVector, version.copy(version = 70001))
    }

  }
}

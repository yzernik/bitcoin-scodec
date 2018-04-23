package lktk.bp2p.messages


import lktk.bmsg.messages._
import scodec.bits.ByteVector
import scodec.bits._
import scodec.codecs._
import java.net.InetSocketAddress
import java.net.InetAddress

import lktk.bmsg.CodecSuite
import lktk.bmsg.messages.Version
import lktk.bmsg.structures.{Message, NetworkAddress}

class VersionSpec extends CodecSuite {

  val version = Version(
    60002,
    1,
    1355854353L,
    NetworkAddress(1, new InetSocketAddress(
      InetAddress.getByAddress(Array(0, 0, 0, 0).map(_.toByte)),
      0)),
    NetworkAddress(1, new InetSocketAddress(
      InetAddress.getByAddress(Array(0, 0, 0, 0).map(_.toByte)),
      0)),
    7284544412836900411L,
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
      roundtrip(Message.codec(0xDAB5BFFAL, 1), version)
      roundtrip(Message.codec(0xD9B4BEF9L, 1), version)
    }

    "decode" in {
      shouldDecodeFullyTo(Version.codec(1), bytesV60002.toBitVector, version)
      shouldDecodeFullyTo(Version.codec(1), bytesV70001.toBitVector, version.copy(version = 70001))
    }

  }
}

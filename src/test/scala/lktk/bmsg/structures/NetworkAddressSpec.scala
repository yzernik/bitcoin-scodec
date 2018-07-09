package lktk.bmsg.structures

import lktk.bmsg.CodecSuite

import java.net.{InetAddress, InetSocketAddress}

import scala.math.BigInt.int2bigInt

import scodec.bits.HexStringSyntax

class NetworkAddressSpec extends CodecSuite {

  val services = ServiceFlags.NodeNetwork
  val ip = InetAddress.getByAddress(Array(10, 0, 0, 1).map(_.toByte))
  val port = 8333
  val addr = new InetSocketAddress(ip, port)

  "NetworkAddress codec" should {
    "roundtrip" in {
      roundtrip(NetworkAddress(ServiceFlags.NodeNetwork | ServiceFlags.NodeXThin, new InetSocketAddress(
        InetAddress.getByAddress(Array(10, 0, 0, 1).map(_.toByte)),
        8080)))
      val bytes = hex"2001 0db8 85a3 0042 1000 8a2e 0370 7334"
      roundtrip(NetworkAddress(1234, new InetSocketAddress(InetAddress.getByAddress(bytes.toArray), 8080)))
    }


    "encode" in {
      NetworkAddress.codec.encode(NetworkAddress(services, addr)).require shouldBe
        hex"01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 FF FF 0A 00 00 01 20 8D".toBitVector
    }

    "decode" in {
      NetworkAddress.codec.decode(hex"01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 FF FF 0A 00 00 01 20 8D".toBitVector).require.value shouldBe
        NetworkAddress(services, addr)
    }

  }
}

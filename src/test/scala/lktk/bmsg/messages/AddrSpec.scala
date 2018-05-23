package lktk.bmsg.messages

import lktk.bmsg.messages
import lktk.bmsg.structures.{Message, NetworkAddress}
import lktk.bmsg.CodecSuite

import java.net.InetAddress
import java.net.InetSocketAddress

import scala.math.BigInt.int2bigInt

import scodec.bits.HexStringSyntax

class AddrSpec extends CodecSuite {

  import lktk.bmsg.messages.Addr._

  val addr = messages.Addr(List((1292899810L,
    NetworkAddress(1, new InetSocketAddress(
      InetAddress.getByAddress(Array(10, 0, 0, 1).map(_.toByte)),
      8333)))))
  val bytes = hex"01 E2 15 10 4D 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 FF FF 0A 00 00 01 20 8D"

  val messageBytes = hex"""
F9 BE B4 D9
61 64 64 72  00 00 00 00 00 00 00 00
1F 00 00 00
ED 52 39 9B  
  """ ++ bytes

  "Addr codec" should {
    "roundtrip" in {
      roundtrip(Addr.codec(1), addr)
      roundtrip(Message.codec(0xDAB5BFFAL, 1), addr)
      roundtrip(Message.codec(0xD9B4BEF9L, 1), addr)
    }

    "decode" in {
      shouldDecodeFullyTo(codec(1), bytes.toBitVector, addr)
    }

  }
}

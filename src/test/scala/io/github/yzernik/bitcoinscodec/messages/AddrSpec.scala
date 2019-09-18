package io.github.yzernik.bitcoinscodec.messages

import java.net.{InetAddress, InetSocketAddress}

import io.github.yzernik.bitcoinscodec.CodecSuite
import io.github.yzernik.bitcoinscodec.structures.{Message, NetworkAddress, UInt64}
import scodec.bits.HexStringSyntax

class AddrSpec extends CodecSuite {

  import Addr._

  val addr = Addr(List((1292899810L,
    NetworkAddress(UInt64(1), new InetSocketAddress(
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
      roundtrip(Message.codec(Message.TESTNET, 1), addr)
      roundtrip(Message.codec(Message.MAINNET, 1), addr)
    }

    "decode" in {
      shouldDecodeFullyTo(codec(1), bytes.toBitVector, addr)
    }

  }
}

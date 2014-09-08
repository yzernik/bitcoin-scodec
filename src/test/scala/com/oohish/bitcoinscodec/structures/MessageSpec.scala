package com.oohish.bitcoinscodec.structures

import scalaz.\/
import scodec.bits._

import com.oohish.bitcoinscodec.CodecSuite

class MessageSpec extends CodecSuite {

  import Message._
  import com.oohish.bitcoinscodec.messages._

  "Message codec" should {

    "roundtrip" in {
      val codec = Message.codec(0xD9B4BEF9L)
      roundtrip(codec, Verack())
      roundtrip(codec, Ping(BigInt(1234)))
      roundtrip(codec, Pong(BigInt(1234)))
      roundtrip(codec, Addr(List((0, NetworkAddress(1234, Left(IPV4("10.0.0.1")), Port(8080))))))
      roundtrip(codec, Version(
        60002,
        1,
        1355854353L,
        NetworkAddress(1, Left(IPV4("0.0.0.0")), Port(0)),
        NetworkAddress(1, Left(IPV4("0.0.0.0")), Port(0)),
        7284544412836900411L,
        "/Satoshi:0.7.2/",
        212672,
        None))
    }

    "encode" in {
      val codec = Message.codec(0xD9B4BEF9L)
      codec.encode(Verack()) shouldBe
        \/.right(hex"F9 BE B4 D9 76 65 72 61  63 6B 00 00 00 00 00 00 00 00 00 00 5D F6 E0 E2".toBitVector)
    }

  }
}

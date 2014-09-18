package com.oohish.bitcoinscodec.structures

import scalaz.\/
import scalaz.-\/
import scalaz.\/-
import scodec.bits._
import com.oohish.bitcoinscodec.CodecSuite
import org.scalatest.Matchers

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
        true))
    }

    "encode" in {
      val codec = Message.codec(0xD9B4BEF9L)
      val verack = Verack()
      val bytes = hex"F9 BE B4 D9 76 65 72 61  63 6B 00 00 00 00 00 00 00 00 00 00 5D F6 E0 E2".toBitVector
      codec.encode(verack) shouldBe
        \/.right(bytes)
    }

    "decode" in {
      val codec = Message.codec(0xD9B4BEF9L)
      val verack = Verack()
      val bytes = hex"F9 BE B4 D9 76 65 72 61  63 6B 00 00 00 00 00 00 00 00 00 00 5D F6 E0 E2".toBitVector
      shouldDecodeFullyTo(codec, bytes, verack)
    }

    "fail to decode message with wrong magic" in {
      val codec = Message.codec(0xD9B4BEF9L)
      val verack = Verack()
      val bytes = hex"F8 BE B4 D9 76 65 72 61  63 6B 00 00 00 00 00 00 00 00 00 00 5D F6 E0 E2".toBitVector
      codec.decode(bytes) shouldBe
        -\/("magic did not match.")
    }

    "fail to decode message with wrong checksum" in {
      val codec = Message.codec(0xD9B4BEF9L)
      val verack = Verack()
      val bytes = hex"F9 BE B4 D9 76 65 72 61  63 6B 00 00 00 00 00 00 00 00 00 00 5D F6 E0 E1".toBitVector
      codec.decode(bytes) shouldBe
        -\/("checksum did not match.")
    }

    "fail to decode message with cut-off payload" in {
      val codec = Message.codec(0xD9B4BEF9L)
      val ping = Ping(BigInt(1234))
      val bytes = hex"f9beb4d970696e67000000000000000040000000433ba813d2040000000000".toBitVector
      codec.decode(bytes) shouldBe
        -\/("cannot acquire 64 bits from a vector that contains 56 bits")
    }

    "fail to decode message with too-long payload" in {
      val codec = Message.codec(0xD9B4BEF9L)
      val ping = Ping(BigInt(1234))
      val bytes = hex"f9beb4d970696e67000000000000000050000000433ba813d20400000000000000".toBitVector
      codec.decode(bytes) shouldBe
        -\/("payload length did not match.")
    }

  }
}

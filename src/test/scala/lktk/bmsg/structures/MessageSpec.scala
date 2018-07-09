package lktk.bmsg.structures

import lktk.bmsg.CodecSuite
import lktk.bmsg.messages._

import java.net.{InetAddress, InetSocketAddress}

import scodec.Attempt.Failure
import scodec.Codec
import scodec.bits._
import scodec.codecs.provide

import scala.math.BigInt.{int2bigInt, long2bigInt}

class MessageSpec extends CodecSuite {
  case class Dummy() extends Message {
    type E = Dummy
    def companion = Dummy
  }

  object Dummy extends MessageCompanion[Dummy] {
    def codec(version: Int): Codec[Dummy] = provide(Dummy())
    def command = "dummy"
  }

  val addr = NetworkAddress(1, new InetSocketAddress(
    InetAddress.getByAddress(Array(0, 0, 0, 0).map(_.toByte)),
    0)
  )

  "Message codec" should {

    "roundtrip" in {
      val codec = Message.codec(0xD9B4BEF9L, 1)
      roundtrip(codec, Verack())
      roundtrip(codec, Addr(List((0.toLong, NetworkAddress(1234, new InetSocketAddress(
        InetAddress.getByAddress(Array(10, 0, 0, 1).map(_.toByte)),
        8080))))))
      roundtrip(codec, Version(
        60002,
        1,
        1355854353L,
        addr,
        addr,
        7284544412836900411L,
        "/Satoshi:0.7.2/",
        212672,
        true))
    }

    "encode" in {
      val codec = Message.codec(0xD9B4BEF9L, 1)
      val verack = Verack()
      val bytes = hex"F9 BE B4 D9 76 65 72 61  63 6B 00 00 00 00 00 00 00 00 00 00 5D F6 E0 E2".toBitVector
      codec.encode(verack).require shouldBe bytes
    }

    "decode" in {
      val codec = Message.codec(0xD9B4BEF9L, 1)
      val verack = Verack()
      val bytes = hex"F9 BE B4 D9 76 65 72 61  63 6B 00 00 00 00 00 00 00 00 00 00 5D F6 E0 E2".toBitVector
      shouldDecodeFullyTo(codec, bytes, verack)
    }

    "fail to decode message with wrong magic" in {
      val codec = Message.codec(0xD9B4BEF9L, 1)
      val bytes = hex"F8 BE B4 D9 76 65 72 61  63 6B 00 00 00 00 00 00 00 00 00 00 5D F6 E0 E2".toBitVector
      codec.decode(bytes) shouldBe Failure(scodec.Err("magic did not match."))
    }

    "fail to decode message with wrong checksum" in {
      val codec = Message.codec(0xD9B4BEF9L, 1)
      val bytes = hex"F9 BE B4 D9 76 65 72 61  63 6B 00 00 00 00 00 00 00 00 00 00 5D F6 E0 E1".toBitVector
      codec.decode(bytes) shouldBe Failure(scodec.Err("checksum did not match."))
    }

    "fail to decode message with cut-off payload" in {
      val codec = TypeMessage.codec(0xD9B4BEF9L, 1)
      val bytes = hex"f9beb4d970696e67000000000000000040000000433ba813d2040000000000".toBitVector
      codec.decode(bytes).isFailure shouldBe true
    }

    "fail to decode message with too-long payload" in {
      val codec = TypeMessage.codec(0xD9B4BEF9L, 1)
      val bytes = hex"f9beb4d970696e67000000000000000050000000433ba813d20400000000000000".toBitVector
      codec.decode(bytes).isFailure shouldBe true
    }

    "fail to encode unrecognized message" in {
      val codec = Message.codec(0xD9B4BEF9L, 1)
      codec.encode(Dummy()) shouldBe Failure(scodec.Err(s"message: dummy not recognized"))
    }

    "fail to decode unrecognized message" in {
      val codec = Message.codec(0xD9B4BEF9L, 1)
      val bytes = hex"0xf9beb4d964756d6d7900000000000000000000005df6e0e2".toBitVector
      codec.decode(bytes) shouldBe Failure(scodec.Err(s"message: dummy not recognized"))
    }
  }
}

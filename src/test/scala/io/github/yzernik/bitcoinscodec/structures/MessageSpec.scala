package io.github.yzernik.bitcoinscodec.structures

import java.net.{InetAddress, InetSocketAddress}

import io.github.yzernik.bitcoinscodec.CodecSuite
import scodec.Attempt.Successful
import scodec.bits._
import scodec.{Attempt, DecodeResult}

class MessageSpec extends CodecSuite {

  import io.github.yzernik.bitcoinscodec.messages._

  "Message codec" should {

    "roundtrip" in {
      val codec = Message.codec(0xD9B4BEF9L, 1)
      roundtrip(codec, Verack())
      roundtrip(codec, Ping(UInt64(1234)))
      roundtrip(codec, Pong(UInt64(1234)))
      roundtrip(codec, Addr(List((0, NetworkAddress(UInt64(1234), new InetSocketAddress(
        InetAddress.getByAddress(Array(10, 0, 0, 1).map(_.toByte)),
        8080))))))
      roundtrip(codec, Version(
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
        true))
    }

    "encode" in {
      val codec = Message.codec(0xD9B4BEF9L, 1)
      val verack = Verack()
      val bytes = hex"F9 BE B4 D9 76 65 72 61  63 6B 00 00 00 00 00 00 00 00 00 00 5D F6 E0 E2"
      codec.encode(verack) shouldBe Successful(bytes.toBitVector)
    }

    "decode" in {
      val codec = Message.codec(0xD9B4BEF9L, 1)
      val verack = Verack()
      val bytes = hex"F9 BE B4 D9 76 65 72 61  63 6B 00 00 00 00 00 00 00 00 00 00 5D F6 E0 E2"

      val Attempt.Successful(DecodeResult(actual, rest)) = codec decode bytes.toBitVector
      rest shouldBe BitVector.empty
      actual shouldBe verack
    }

    "fail to decode message with wrong magic" in {
      val codec = Message.codec(0xD9B4BEF9L, 1)
      val bytes = hex"F8 BE B4 D9 76 65 72 61  63 6B 00 00 00 00 00 00 00 00 00 00 5D F6 E0 E2"
      codec.decode(bytes.toBitVector) shouldBe Attempt.Failure(scodec.Err("magic did not match."))
    }

    "fail to decode message with wrong checksum" in {
      val codec = Message.codec(0xD9B4BEF9L, 1)
      val bytes = hex"F9 BE B4 D9 76 65 72 61  63 6B 00 00 00 00 00 00 00 00 00 00 5D F6 E0 E1"
      codec.decode(bytes.toBitVector) shouldBe Attempt.Failure(scodec.Err("checksum did not match."))
    }

    "fail to decode message with cut-off payload" in {
      val codec = Message.codec(0xD9B4BEF9L, 1)
      val ping = Ping.generate
      val bitVector: BitVector = codec.encode(ping).toOption.get
      val truncatedVector = bitVector.dropRight(1)
      codec.decode(truncatedVector) shouldBe Attempt.Failure(scodec.Err("payload is less than specified length."))
    }

    "decode message with remaining bits" in {
      val codec = Message.codec(0xD9B4BEF9L, 1)
      val ping = Ping.generate
      val bitVector: BitVector = codec.encode(ping).toOption.get
      val paddedVector = bitVector ++ BitVector.high(32)

      val Attempt.Successful(DecodeResult(actual, rest)) = codec.decode(paddedVector)
      actual shouldBe ping
      rest shouldBe BitVector.high(32)
    }

  }
}

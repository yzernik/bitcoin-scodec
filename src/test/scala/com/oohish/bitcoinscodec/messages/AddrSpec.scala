package com.oohish.bitcoinscodec.messages

import com.oohish.bitcoinscodec.CodecSuite

import scodec.bits.ByteVector
import scodec.bits._
import scodec.codecs._
import scalaz.\/

class AddrSpec extends CodecSuite {

  import Addr._

  val addr = Addr(List((1292899810L, NetworkAddress(1, Left(IPV4("10.0.0.1")), Port(8333)))))
  val bytes = hex"01 E2 15 10 4D 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 FF FF 0A 00 00 01 20 8D"

  "Addr codec" should {
    "roundtrip" in {
      roundtrip(Addr(List((0, NetworkAddress(1234, Left(IPV4("10.0.0.1")), Port(8080))))))
    }

    "encode" in {
      codec.encode(addr) shouldBe
        \/.right(bytes.toBitVector)
    }

    "decode" in {
      codec.decode(bytes.toBitVector) shouldBe
        \/.right(BitVector.empty, addr)
    }

  }
}

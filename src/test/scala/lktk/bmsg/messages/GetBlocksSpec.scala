package lktk.bmsg.messages

import lktk.bmsg.CodecSuite

import lktk.bmsg.structures.{Hash, Message}

import scodec.bits.ByteVector

class GetBlocksSpec extends CodecSuite {
  val getblocks = GetBlocks(
    1L,
    List(Hash(ByteVector.fill(32)(0x42))),
    Hash(ByteVector.fill(32)(0x42)))

  "GetBlocks codec" should {
    "roundtrip" in {
      roundtrip(GetBlocks.codec(1), getblocks)
      roundtrip(Message.codec(0xDAB5BFFAL, 1), getblocks)
    }

  }
}

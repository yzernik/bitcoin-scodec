package lktk.bp2p.messages

import lktk.bmsg.CodecSuite
import lktk.bmsg.messages.GetBlocks
import lktk.bmsg.structures.{Hash, Message}

import lktk.bmsg.messages._
import scodec.bits.ByteVector
import scodec.bits._
import scodec.codecs._

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

package lktk.bp2p.messages

import lktk.bchmsg.CodecSuite
import lktk.bchmsg.messages.GetBlocks
import lktk.bchmsg.structures.{Hash, Message}

import lktk.bchmsg.messages._
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

package lktk.bchmsg.messages

import lktk.bchmsg.CodecSuite
import lktk.bchmsg.structures._
import lktk.bchmsg.messages.GetBlocks
import lktk.bchmsg.structures.{Hash, Message}

class GetBlocksSpec extends CodecSuite {

  import GetBlocks._

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

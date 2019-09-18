package io.github.yzernik.bitcoinscodec.messages

import io.github.yzernik.bitcoinscodec.CodecSuite
import io.github.yzernik.bitcoinscodec.structures._
import scodec.bits.ByteVector

class GetBlocksSpec extends CodecSuite {

  val getblocks = GetBlocks(
    1L,
    List(Hash(ByteVector.fill(32)(0x42))),
    Hash(ByteVector.fill(32)(0x42)))

  "GetBlocks codec" should {
    "roundtrip" in {
      roundtrip(GetBlocks.codec(1), getblocks)
      roundtrip(Message.codec(Message.TESTNET, 1), getblocks)
    }

  }
}

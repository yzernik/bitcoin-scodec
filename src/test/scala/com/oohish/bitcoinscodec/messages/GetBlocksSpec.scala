package com.oohish.bitcoinscodec.messages

import com.oohish.bitcoinscodec.CodecSuite
import scodec.bits.ByteVector
import scodec.bits._
import scodec.codecs._
import scalaz.\/
import com.oohish.bitcoinscodec.structures._

class GetBlocksSpec extends CodecSuite {

  import GetBlocks._

  val getblocks = GetBlocks(
    1L,
    1L,
    List(Hash(ByteVector.fill(32)(0x42))),
    Hash(ByteVector.fill(32)(0x42)))

  "GetBlocks codec" should {
    "roundtrip" in {
      roundtrip(getblocks)
      roundtrip(Message.codec(0xDAB5BFFAL), getblocks)
    }

  }
}

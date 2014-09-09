package com.oohish.bitcoinscodec.structures

import com.oohish.bitcoinscodec.CodecSuite
import scodec.bits.ByteVector
import scodec.bits._
import scodec.codecs._
import scalaz.\/

class BlockHeaderSpec extends CodecSuite {

  import BlockHeader._

  val blockheader = BlockHeader(
    1L,
    Hash(ByteVector.fill(32)(0x42)),
    Hash(ByteVector.fill(32)(0x42)),
    1355854353L,
    1234L,
    212672)

  "BlockHeader codec" should {
    "roundtrip" in {
      roundtrip(blockheader)
    }

  }
}

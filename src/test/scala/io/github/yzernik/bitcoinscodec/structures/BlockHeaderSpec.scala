package io.github.yzernik.bitcoinscodec.structures

import io.github.yzernik.bitcoinscodec.CodecSuite
import io.github.yzernik.bitcoinscodec.util.Util
import scodec.bits._

class BlockHeaderSpec extends CodecSuite {

  val blockheader = BlockHeader(
    1L,
    Hash(hex"0000000000000000000000000000000000000000000000000000000000000000"),
    Hash(hex"4a5e1e4baab89f3a32518a88c31bc87f618f76673e2cc77ab2127b7afdeda33b"),
    1231006505L,
    486604799L,
    2083236893L)

  val blockheader2 = BlockHeader(
    1L,
    Hash(hex"0000000000000000000000000000000000000000000000000000000000000000"),
    Hash(hex"4a5e1e4baab89f3a32518a88c31bc87f618f76673e2cc77ab2127b7afdeda33b"),
    1231006505L,
    486604799L,
    Util.generateNonce32)

  "BlockHeader codec" should {
    "roundtrip" in {
      roundtrip(blockheader)
      roundtrip(blockheader2)
    }

    "decode" in {
      val bytes = hex"""        
    		  01000000
    		  0000000000000000000000000000000000000000000000000000000000000000
    		  3BA3EDFD7A7B12B27AC72C3E67768F617FC81BC3888A51323A9FB8AA4B1E5E4A
    		  29AB5F49
    		  FFFF001D
    		  1DAC2B7C        
        """.toBitVector
      shouldDecodeFullyTo(BlockHeader.codec, bytes, blockheader)
    }

  }
}

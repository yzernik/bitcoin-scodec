package com.github.yzernik.bitcoinscodec.structures

import com.github.yzernik.bitcoinscodec.CodecSuite
import scodec.bits.ByteVector
import scodec.bits._

class OutPointSpec extends CodecSuite {

  import OutPoint._

  "OutPoint codec" should {

    "roundtrip" in {
      OutPoint(
        Hash(ByteVector.fill(32)(0x42)),
        55555L)
    }

    "decode" in {
      val outpoint =
        OutPoint(
          Hash(hex"6dbddb085b1d8af75184f0bc01fad58d1266e9b63b50881990e4b40d6aee3629".reverse),
          0L)
      val bytes = hex"""            
       6D BD DB 08 5B 1D 8A F7  51 84 F0 BC 01 FA D5 8D
 12 66 E9 B6 3B 50 88 19  90 E4 B4 0D 6A EE 36 29
 00 00 00 00    
      """.toBitVector
      shouldDecodeFullyTo(OutPoint.codec, bytes, outpoint)
    }

  }
}
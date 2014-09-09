package com.oohish.bitcoinscodec.structures

import com.oohish.bitcoinscodec.CodecSuite
import scodec.bits.ByteVector
import scodec.bits._

class TxOutSpec extends CodecSuite {

  import TxOut._

  "TxOut codec" should {

    "roundtrip" in {
      roundtrip(TxOut(
        123456L,
        hex"123456"))
    }

  }
}

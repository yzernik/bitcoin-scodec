package com.github.yzernik.bitcoinscodec.structures

import com.github.yzernik.bitcoinscodec.CodecSuite
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

    "decode" in {
      val txout = TxOut(
        4632880204564398080L,
        hex"76a9141aa0cd1cbea6e7458a7abad512a9d9ea1afb225e88ac")
      val bytes = hex"""            
 40 4B 4C 00 00 00 00 00                          
 19                                                
 76 A9 14 1A A0 CD 1C BE  A6 E7 45 8A 7A BA D5 12 
 A9 D9 EA 1A FB 22 5E 88  AC                       
      """.toBitVector
      shouldDecodeFullyTo(TxOut.codec, bytes, txout)
    }

  }
}

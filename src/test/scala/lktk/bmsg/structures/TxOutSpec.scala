package lktk.bmsg.structures

import lktk.bmsg.CodecSuite
import lktk.bmsg.structures._

import scodec.bits._

class TxOutSpec extends CodecSuite {

  "TxOut codec" should {

    "roundtrip" in {
      roundtrip(TxOut(
        123456L,
        hex"123456"))
    }

    "decode" in {
      val txOut = TxOut(
        5000000,
        hex"76a9141aa0cd1cbea6e7458a7abad512a9d9ea1afb225e88ac")

      val bytes = (
        hex"40 4B 4C 00 00 00 00 00" ++ //5000000
        hex"19" ++ //Script Size
        hex"76 A9 14 1A A0 CD 1C BE A6 E7 45 8A 7A BA D5 12 A9 D9 EA 1A FB 22 5E 88 AC" //Script
      ).toBitVector
      shouldDecodeFullyTo(TxOut.codec, bytes, txOut)
    }

    "roundtrip of list" in {
      val txouts = List(
        TxOut(
          5000000,
          hex"76a9141aa0cd1cbea6e7458a7abad512a9d9ea1afb225e88ac"),
        TxOut(
          3354000000L,
          hex"76a9140eab5bea436a0484cfab12485efda0b78b4ecc5288ac"))

      roundtrip(VarList.varList(TxOut.codec), txouts)

    }

  }
}

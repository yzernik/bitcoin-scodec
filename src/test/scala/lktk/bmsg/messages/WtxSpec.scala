package lktk.bmsg.messages

import lktk.bmsg.{CodecSuite, TestTxs}

class WtxSpec extends CodecSuite {

  "Wtx codec" should {

    "p2wpkh roundtrip" in {
      roundtrip(Tx0.codec(1), TestTxs.p2wpkh)
    }

    "decode p2wpkh" in {
      shouldDecodeFullyTo(Tx0.codec(1), TestTxs.bp2wpkh.toBitVector, TestTxs.p2wpkh)
    }

    "decode p2wpsh" in {
      shouldDecodeFullyTo(Tx0.codec(1), TestTxs.bp2wsh.toBitVector, TestTxs.p2wsh)
    }
  }
}

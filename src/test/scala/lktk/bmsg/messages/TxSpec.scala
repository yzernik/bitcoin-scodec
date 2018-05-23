package lktk.bmsg.messages

import lktk.bmsg.{CodecSuite, TestTxs}
import lktk.bmsg.structures._
import lktk.bmsg.util.BitcoinCashParams

class TxSpec extends CodecSuite {

  "Tx codec" should {

    "empty roundtrip" in {
      val tx1 = Tx(
        1L,
        List(),
        List(),
        12345L)
      roundtrip(Tx0.codec(1), tx1)
      roundtrip(Message.codec(0xDAB5BFFAL, 1), tx1)
    }

    "roundtrip coinbase" in {
      roundtrip(Tx0.codec(1), TestTxs.bcoinbase)
      roundtrip(Tx0.codec(1), TestTxs.coinbase)
    }

    "roundtrip" in {
      roundtrip(Tx0.codec(1), TestTxs.p2pkh)
      roundtrip(Message.codec(BitcoinCashParams.testnet, 1), TestTxs.p2pkh)
    }

    "roundtrip bytes" in {
      roundtrip(Tx0.codec(1), TestTxs.bp2pkh)
      roundtrip(Tx0.codec(1), TestTxs.bp2pkh1)
      roundtrip(Tx0.codec(1), TestTxs.bp2pkh2)
      roundtrip(Tx0.codec(1), TestTxs.bp2sh)
    }

    "decode" in {
      shouldDecodeFullyTo(Tx0.codec(1), TestTxs.bp2pkh.toBitVector, TestTxs.p2pkh)
    }
  }
}

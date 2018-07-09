package lktk.bmsg.structures

import lktk.bmsg.CodecSuite
import lktk.bmsg.messages.Tx
import lktk.bmsg.util.{BitcoinCashParams, ProtocolVersion}
import scodec.bits.{ByteVector, _}

class PrefilexTxSpec extends CodecSuite {

  "PrefilexTx codec" should {

    val version = 1L
    val txins = List(
      TxIn(
        OutPoint(Hash(hex"6dbddb085b1d8af75184f0bc01fad58d1266e9b63b50881990e4b40d6aee3629".reverse),
          0),
        hex"483045022100f3581e1972ae8ac7c7367a7a253bc1135223adb9a468bb3a59233f45bc578380022059af01ca17d00e41837a1d58e97aa31bae584edec28d35bd96923690913bae9a0141049c02bfc97ef236ce6d8fe5d94013c721e915982acd2b12b65d9b7d59e20a842005f8fc4e02532e873d37b96f09d6d4511ada8f14042f46614a4c70c0f14beff5",
        4294967295L))

    val txouts = List(
      TxOut(
        4632880204564398080L,
        hex"76a9141aa0cd1cbea6e7458a7abad512a9d9ea1afb225e88ac"),
      TxOut(
        -9152746251769348096L,
        hex"76a9140eab5bea436a0484cfab12485efda0b78b4ecc5288ac"))
    val locktime = 0L

    val tx = Tx(version, txins, txouts, locktime)

    val prefiledTx = PrefilledTx(1L, tx)

    "roundtrip" in {
      roundtrip(PrefilledTx.codec(ProtocolVersion.shortIdsBlocksVersion), prefiledTx)
    }
  }
}

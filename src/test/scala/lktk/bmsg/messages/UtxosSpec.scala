package lktk.bmsg.messages

import lktk.bmsg.CodecSuite
import lktk.bmsg.structures._
import lktk.bmsg.util.BitcoinCashParams
import org.scalacheck.Gen
import scodec.bits._

class UtxosSpec extends CodecSuite {

  val rUtxo = ResultUtxo(1L, 5555L, TxOut(123456L, hex"123456"))

  def utxosGen: Gen[Utxos] = for {
    chanHeight <- Gen.choose(1, 4294967295L)
    size <- Gen.choose(1, 36000)
    filter <- Gen.containerOfN[Seq, Byte](size, Gen.choose(0, 100).map(_.toByte))
    listResultUtxo <- Gen.containerOfN[List, ResultUtxo](size, Gen.const(rUtxo))
  } yield Utxos(chanHeight, Hash(ByteVector.fill(32)(0x42)), ByteVector(filter), listResultUtxo)


  "Utxos codec" should {
    "roundtrip" in {
      utxosGen.map { utxos =>
        roundtrip(Utxos.codec(1), utxos)
        roundtrip(Message.codec(BitcoinCashParams.testnet, 1), utxos)
      }.sample
    }
  }
}

package lktk.bmsg.messages

import lktk.bmsg.CodecSuite
import lktk.bmsg.structures.{Hash, Message, OutPoint}
import lktk.bmsg.util.BitcoinCashParams
import org.scalacheck.Gen
import scodec.bits._

class GetUtxoSpec extends CodecSuite {

  val outpoint =
    OutPoint(
      Hash(hex"6dbddb085b1d8af75184f0bc01fad58d1266e9b63b50881990e4b40d6aee3629".reverse),
      0L
    )

  val getUtxoGen = for {
    num <- Gen.choose(1, 100)
    list <- Gen.containerOfN[List, OutPoint](num, Gen.const(outpoint))
    bool <- Gen.oneOf(true, false)
  } yield GetUtxo(bool, list)

  "GetUtxo codec" should {
    "roundtrip" in {
      getUtxoGen.map { getutxo =>
        roundtrip(GetUtxo.codec(1), getutxo)
        roundtrip(Message.codec(BitcoinCashParams.testnet, 1), getutxo)
      }.sample
    }
  }
}

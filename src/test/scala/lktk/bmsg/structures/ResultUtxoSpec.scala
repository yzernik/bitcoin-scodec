package lktk.bmsg.structures

import lktk.bmsg.CodecSuite
import scodec.bits._

class ResultUtxoSpec extends CodecSuite {

  val rUtxo = ResultUtxo(1L, 5555L, TxOut(123456L, hex"123456"))

  "ResultUtxo codec" should {
    "roundtrip" in {
      roundtrip(ResultUtxo.codec, rUtxo)
    }
  }
}
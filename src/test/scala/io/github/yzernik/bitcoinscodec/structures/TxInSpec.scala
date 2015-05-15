package io.github.yzernik.bitcoinscodec.structures

import io.github.yzernik.bitcoinscodec.CodecSuite
import scodec.bits.ByteVector
import scodec.bits._

class TxInSpec extends CodecSuite {

  import TxIn._

  "TxIn codec" should {

    "roundtrip" in {
      roundtrip(TxIn(
        OutPoint(
          Hash(ByteVector.fill(32)(0x42)),
          55555L),
        ByteVector(0x123456),
        111111L))
    }

    "decode" in {
      val txin = TxIn(
        OutPoint(
          Hash(hex"6dbddb085b1d8af75184f0bc01fad58d1266e9b63b50881990e4b40d6aee3629".reverse),
          0L),
        hex"""            
        483045022100f3581e1972ae8ac7c7367a7a253bc1135223
            adb9a468bb3a59233f45bc578380022059af01ca17d00e4183
            7a1d58e97aa31bae584edec28d35bd96923690913bae9a0141049
            c02bfc97ef236ce6d8fe5d94013c721e915982acd2b12b65d9b7d59e
            20a842005f8fc4e02532e873d37b96f09d6d4511ada8f14042f46614a4c
            70c0f14beff5        
        """,
        4294967295L)
      val bytes = hex"""            
       6D BD DB 08 5B 1D 8A F7  51 84 F0 BC 01 FA D5 8D
 12 66 E9 B6 3B 50 88 19  90 E4 B4 0D 6A EE 36 29
 00 00 00 00

 8B                                                

 48 30 45 02 21 00 F3 58  1E 19 72 AE 8A C7 C7 36  
 7A 7A 25 3B C1 13 52 23  AD B9 A4 68 BB 3A 59 23
 3F 45 BC 57 83 80 02 20  59 AF 01 CA 17 D0 0E 41
 83 7A 1D 58 E9 7A A3 1B  AE 58 4E DE C2 8D 35 BD
 96 92 36 90 91 3B AE 9A  01 41 04 9C 02 BF C9 7E
 F2 36 CE 6D 8F E5 D9 40  13 C7 21 E9 15 98 2A CD
 2B 12 B6 5D 9B 7D 59 E2  0A 84 20 05 F8 FC 4E 02
 53 2E 87 3D 37 B9 6F 09  D6 D4 51 1A DA 8F 14 04
 2F 46 61 4A 4C 70 C0 F1  4B EF F5

 FF FF FF FF                                           
      """.toBitVector
      shouldDecodeFullyTo(TxIn.codec, bytes, txin)
    }

  }
}

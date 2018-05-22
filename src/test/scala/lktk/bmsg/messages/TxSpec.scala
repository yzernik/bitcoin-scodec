package lktk.bp2p.messages

import lktk.bmsg.CodecSuite
import lktk.bmsg.messages.{Tx, Tx0}
import lktk.bmsg.structures._
import lktk.bmsg.util.BitcoinCashParams

import scodec.bits._

class TxSpec extends CodecSuite {

  val bytes = hex"""
01 00 00 00 01 6D BD DB 
08 5B 1D 8A F7 51 84 F0  BC 01 FA D5 8D 12 66 E9 
B6 3B 50 88 19 90 E4 B4  0D 6A EE 36 29 00 00 00 
00 8B 48 30 45 02 21 00  F3 58 1E 19 72 AE 8A C7 
C7 36 7A 7A 25 3B C1 13  52 23 AD B9 A4 68 BB 3A 
59 23 3F 45 BC 57 83 80  02 20 59 AF 01 CA 17 D0 
0E 41 83 7A 1D 58 E9 7A  A3 1B AE 58 4E DE C2 8D 
35 BD 96 92 36 90 91 3B  AE 9A 01 41 04 9C 02 BF 
C9 7E F2 36 CE 6D 8F E5  D9 40 13 C7 21 E9 15 98  
2A CD 2B 12 B6 5D 9B 7D  59 E2 0A 84 20 05 F8 FC 
4E 02 53 2E 87 3D 37 B9  6F 09 D6 D4 51 1A DA 8F 
14 04 2F 46 61 4A 4C 70  C0 F1 4B EF F5 FF FF FF 
FF 02 40 4B 4C 00 00 00  00 00 19 76 A9 14 1A A0 
CD 1C BE A6 E7 45 8A 7A  BA D5 12 A9 D9 EA 1A FB
22 5E 88 AC 80 FA E9 C7  00 00 00 00 19 76 A9 14 
0E AB 5B EA 43 6A 04 84  CF AB 12 48 5E FD A0 B7 
8B 4E CC 52 88 AC 00 00  00 00                   
"""


  val otherTx = hex"0100000003b0c03779c38ddabc044947b164adbcffde8e0ddb3678494f4e8b83f13b37629d010000006b483045022100f3c0f555cd39198caf77f6756256801fc57bcabe4892601b9def52259698f40a0220230262841bff151eb617f7d4cdda6bf06d6fa791230c152e47f68bcdd0e6f64a01210204b3506d8903ca601c97a4abab6548e91004c535a5a45e21299a494b146859caffffffff060eeae54b70d12caddcc15f8897ebc2b6c3011c9a600aba74d49b969991cab3000000006b4830450221009dbeb64ddd4646e1118503c87feaa95c531b5178c6e543be782f6ecb05e8fbe602203945570f8dc56c145617f0283fa4032e0b7895a9cf2a81c7b65665b8ac608cb00121026602a5dc59c30f485b2c457ac8e2f617e27b10a1d2ae76f3231f9b01dff08964ffffffff7f715ba408db0289118f9b8578e54e721f1aee10d09844510793b8b20c87903e010000006b48304502210095e300886ec4df78e39d6d0cf5e5e531ded8f42f00e5730c371d8951867dad5b02203c55f9403f6c2aac444213161a93a86661e7367d4007be74ffb3981387cc1c790121023f0aadfeb71a4964c5087fec8b052c8236051dae838d4747543330c6b266ce6effffffff02d5781100000000001976a914342ab422c9e3ef285efe9882ae54269ed9713dd688ac20a10700000000001976a91488d924f51033b74a895863a5fb57fd545529df7d88ac00000000"


  val txins = List(
    TxIn(
      OutPoint(Hash(hex"2936ee6a0db4e4901988503bb6e966128dd5fa01bcf08451f78a1d5b08dbbd6d"), 0),
      hex"483045022100f3581e1972ae8ac7c7367a7a253bc1135223adb9a468bb3a59233f45bc578380022059af01ca17d00e41837a1d58e97aa31bae" ++
        hex"584edec28d35bd96923690913bae9a0141049c02bfc97ef236ce6d8fe5d94013c721e915982acd2b12b65d9b7d59e20a842005f8fc4e02532e873d37b96f09d6d4511ada8f14042f46614a4c70c0f14beff5",
      4294967295L))

  val version = 1L

  val txouts = List(
    TxOut(
      5000000,
      hex"76a9141aa0cd1cbea6e7458a7abad512a9d9ea1afb225e88ac"),
    TxOut(
      3354000000L,
      hex"76a9140eab5bea436a0484cfab12485efda0b78b4ecc5288ac"))
  val locktime = 0L

  val tx = Tx(version, txins, txouts, locktime)

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

    "roundtrip" in {
      roundtrip(Tx0.codec(1), tx)
      roundtrip(Message.codec(BitcoinCashParams.testnet, 1), tx)
    }

    "decode" in {
      shouldDecodeFullyTo(Tx0.codec(1), bytes.toBitVector, tx)
    }
  }
}

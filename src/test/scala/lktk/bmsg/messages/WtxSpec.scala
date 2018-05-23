package lktk.bmsg.messages

import lktk.bmsg.CodecSuite
import lktk.bmsg.structures._
import scodec.bits._

class WtxSpec extends CodecSuite {

  val bytesP2WPKH = hex"0100000000010115e180dc28a2327e687facc33f10f2a20da717e5548406f7ae8b4c811072f8560200000000ffffffff0188b3f505000000001976a9141d7cd6c75c2e86f4cbf98eaed221b30bd9a0b92888ac02483045022100f9d3fe35f5ec8ceb07d3db95adcedac446f3b19a8f3174e7e8f904b1594d5b43022074d995d89a278bd874d45d0aea835d3936140397392698b7b5bbcdef8d08f2fd012321038262a6c6cec93c2d3ecd6c6072efea86d02ff8e3328bbd0242b20af3425990acac00000000"

  val bytesP2WSH = hex"0100000000010189dd629a094838d8001991f165f79bce833f7fa8f2989d035b5e18e7c797dfdc0300000000ffffffff025ab41f5e000000001976a914bf151342d2859d496036e357d569ab767e07243d88ac56c2231900000000220020701a8d401c84fb13e6baf169d59684e17abd9fa216c8cc5b9fc63d622ff8c58d0400473044022006254598e8747e62d71186f0b0a503386f36de259feb1323fd6212c5eff6ce240220356fc4443490af3efebf43b8f1227b589504c23147c125c00d6b9a1f18cb0c270147304402204751c5634bf2be5150dad317125eedfbf6eb733475178e062c017c77eee696eb02202e31e95dcd35b4fc630014f43cc40e1ea9d2324ed3582dbe7b20f417afbca43f016952210266edd4ef2953675faf0662c088a7f620935807d200d65387290b31648e51e253210372ce38027ee95c98cdc54172964fa3aecf9f24b85c139d3d203365d6b691d0502103c96d495bfdd5ba4145e3e046fee45e84a8a48ad05bd8dbb395c011a32cf9f88053ae00000000"


  val p2wpkh = Wtx(
    1,
    List(TxIn(
      OutPoint(Hash(hex"56f87210814c8baef7068454e517a70da2f2103fc3ac7f687e32a228dc80e115"),2),
      ByteVector.empty,
      4294967295L
    )),
    List(TxOut(99988360, hex"76a9141d7cd6c75c2e86f4cbf98eaed221b30bd9a0b92888ac")),
    List(
      ScriptWitness(hex"3045022100f9d3fe35f5ec8ceb07d3db95adcedac446f3b19a8f3174e7e8f904b1594d5b43022074d995d89a278bd874d45d0aea835d3936140397392698b7b5bbcdef8d08f2fd01"),
      ScriptWitness(hex"21038262a6c6cec93c2d3ecd6c6072efea86d02ff8e3328bbd0242b20af3425990acac")
    ),
    0
  )

  val p2wsh = Wtx(
    1,
    List(TxIn(OutPoint(Hash(hex"dcdf97c7e7185e5b039d98f2a87f3f83ce9bf765f1911900d83848099a62dd89"),3),ByteVector.empty,4294967295L)),
    List(
      TxOut(1579136090,hex"76a914bf151342d2859d496036e357d569ab767e07243d88ac"),
      TxOut(421773910, hex"0020701a8d401c84fb13e6baf169d59684e17abd9fa216c8cc5b9fc63d622ff8c58d")
    ),
    List(
      ScriptWitness(ByteVector.empty),
      ScriptWitness(hex"3044022006254598e8747e 62d71186f0b0a503386f36de259feb1323fd6212c5eff6ce240220356fc4443490af3efebf43b8f1227b589504c23147c125c00d6b9a1f18cb0c2701"),
      ScriptWitness(hex"304402204751c5634bf2be5150dad317125eedfbf6eb733475178e062c017c77eee696eb02202e31e95dcd35b4fc630014f43cc40e1ea9d2324ed3582dbe7b20f417afbca43f01"),
      ScriptWitness(hex"52210266edd4ef2953675faf0662c088a7f620935807d200d65387290b31648e51e253210372ce38027ee95c98cdc54172964fa3aecf9f24b85c139d3d203365d6b691d0502103c96d495bfdd5ba4145e3e046fee45e84a8a48ad05bd8dbb395c011a32cf9f88053ae")),0)

  "Wtx codec" should {

    "empty roundtrip" in {
      val tx1 = Wtx(
        1L,
        List(),
        List(),
        List(),
        0L)
      roundtrip(Tx0.codec(1), tx1)
      roundtrip(Message.codec(0xDAB5BFFAL, 1), tx1)

    }

    "p2wpkh roundtrip" in {
      roundtrip(Tx0.codec(1), p2wpkh)
    }

    "decode p2wpkh" in {
      shouldDecodeFullyTo(Tx0.codec(1), bytesP2WPKH.toBitVector, p2wpkh)
    }

    "decode p2wpsh" in {
      shouldDecodeFullyTo(Tx0.codec(1), bytesP2WSH.toBitVector, p2wsh)
    }
  }
}

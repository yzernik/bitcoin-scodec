package lktk.bmsg.messages

import lktk.bmsg.structures._
import scodec.codecs._
import scodec.Codec
import scodec.bits.ByteVector

//BIP64
case class Utxos(
  chainHeight: Long,
  chainTip: Hash,
  hitBitmap: ByteVector,
  resultUtxos: List[ResultUtxo]
) extends Message {
  type E = Utxos
  def companion = Utxos
}

object Utxos extends MessageCompanion[Utxos] {

  def hitBitMap = variableSizeBitsLong[ByteVector](VarInt.varIntCodec, bytes)

  def codec(version: Int): Codec[Utxos] = (
    ("chainHeight" | uint32L) ::
    ("chainTip" | Hash.codec) ::
    ("hitBitmap" | hitBitMap) ::
    ("resultUtxos" | VarList.varList(ResultUtxo.codec))
  ).as[Utxos]

  def command = "utxos"
}
package lktk.bmsg.messages

import lktk.bmsg.structures.{Message, MessageCompanion, OutPoint, VarList}
import scodec.Codec
import scodec.codecs._

//BIP64
case class GetUtxo(
  checkMempool: Boolean,
  outpoints: List[OutPoint]
) extends Message {
  type E = GetUtxo
  def companion = GetUtxo
}

object GetUtxo extends MessageCompanion[GetUtxo] {

  def checkMempool = ("checkMempool" | mappedEnum(uint8, false -> 0, true -> 1)).as[Boolean]

  def codec(version: Int): Codec[GetUtxo] = (
    checkMempool :: ("outpoints" | VarList.varList(OutPoint.codec))
  ).as[GetUtxo]

  def command = "getutxo"
}
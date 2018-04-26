package lktk.bmsg.messages

import lktk.bmsg.structures._

import scodec.codecs._

//BIP152
case class GetBlockTxn(
  hash: Hash,
  indexes: List[Long]
) extends Message {
  type E = GetBlockTxn
  def companion = GetBlockTxn
}

object GetBlockTxn extends MessageCompanion[GetBlockTxn] {
  def codec(version: Int) = (
    ("hash" | Hash.codec) ::
    VarList.varList(uint32)
  ).as[GetBlockTxn]

  def command = "getblocktxn"
}



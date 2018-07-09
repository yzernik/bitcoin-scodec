package lktk.bmsg.structures

import lktk.bmsg.messages.Tx0

import scodec.codecs._

//BIP152
case class BlockTxn(
  hash: Hash,
  txs: List[Tx0]
) extends Message {
  type E = BlockTxn
  def companion = BlockTxn
}

object BlockTxn extends MessageCompanion[BlockTxn] {
  def codec(version: Int) =
    (("hash" | Hash.codec) ::
      VarList.varList(Tx0.codec(version))
    ).as[BlockTxn]

  def command = "blocktxn"
}


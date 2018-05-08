package lktk.bmsg.structures

import lktk.bmsg.messages.Tx

import scodec.codecs._

//BIP152
case class BlockTxn(
  hash: Hash,
  txs: List[Tx]
) extends Message {
  type E = BlockTxn
  def companion = BlockTxn
}

object BlockTxn extends MessageCompanion[BlockTxn] {
  def codec(version: Int) =
    (("hash" | Hash.codec) ::
      VarList.varList(Tx.codec(version))
    ).as[BlockTxn]

  def command = "blocktxn"
}


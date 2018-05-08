package lktk.bmsg.messages

import lktk.bmsg.structures._
import lktk.bmsg.structures.UInt64.bigIntCodec

import scodec.Codec
import scodec.codecs._

//BIP152
//HeaderAndShortIDs
case class CmpctBlock(
  header: BlockHeader,
  nonce: BigInt,
  shortIds: List[BigInt],
  prefilledTxn: List[PrefiledTx]
) extends Message {
  type E = CmpctBlock
  def companion = CmpctBlock
}

object CmpctBlock extends MessageCompanion[CmpctBlock] {
  def codec(version: Int): Codec[CmpctBlock] = (
    ("header" | BlockHeader.codec) ::
    ("nonce" | Codec[BigInt]) ::
    VarList.varList(Codec[BigInt]) ::
    VarList.varList(PrefiledTx.codec(version))
  ).as[CmpctBlock]

  def command = "cmpctblock"
}

package lktk.bmsg.messages

import lktk.bmsg.structures.{Message, MessageCompanion}
import lktk.bmsg.structures.UInt64.bigIntCodec

import scodec.Codec
import scodec.codecs._

//Added in BIP152
case class CmptcBlock(
  announce: Boolean,
  version: BigInt) extends Message {
  type E = CmptcBlock
  def companion = CmptcBlock
}

object CmptcBlock extends MessageCompanion[CmptcBlock] {
  def codec(version: Int): Codec[CmptcBlock] =
    (
      ("announce" | mappedEnum(uint8, false -> 0, true -> 1)).as[Boolean] ::
      ("version" | Codec[BigInt])
    ).as[CmptcBlock] //placeholder
  def command = "cmptcblock"
}
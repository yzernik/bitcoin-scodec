package lktk.bmsg.messages

import lktk.bmsg.structures.{Hash, Message, MessageCompanion, VarList}

import scodec.Codec
import scodec.bits.ByteVector
import scodec.codecs._

case class GetHeaders(
  version: Long,
  blockLocatorHashes: List[Hash],
  hashStop: Hash = GetBlocks.zeroStop) extends Message {
  type E = GetHeaders
  def companion = GetHeaders
}

object GetHeaders extends MessageCompanion[GetHeaders] {
  val zeroStop = Hash(ByteVector.fill(32)(0))
  def codec(version: Int): Codec[GetHeaders] = {
    ("version" | uint32L) ::
      ("blockLocatorHashes" | VarList.varList(Codec[Hash])) ::
      ("hashStop" | Codec[Hash])
  }.as[GetHeaders]

  def command = "getheaders"
}

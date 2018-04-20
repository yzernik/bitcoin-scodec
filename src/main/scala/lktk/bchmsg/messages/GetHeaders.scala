package lktk.bchmsg.messages

import lktk.bchmsg.structures.Hash
import lktk.bchmsg.structures.Message
import lktk.bchmsg.structures.MessageCompanion
import lktk.bchmsg.structures.VarList
import lktk.bchmsg.structures.{Hash, Message, MessageCompanion, VarList}

case class GetHeaders(
  version: Long,
  block_locator_hashes: List[Hash],
  hash_stop: Hash = GetBlocks.zeroStop) extends Message {
  type E = GetHeaders
  def companion = GetHeaders
}

object GetHeaders extends MessageCompanion[GetHeaders] {
  val zeroStop = Hash(ByteVector.fill(32)(0))
  def codec(version: Int): Codec[GetHeaders] = {
    ("version" | uint32L) ::
      ("block_locator_hashes" | VarList.varList(Codec[Hash])) ::
      ("hash_stop" | Codec[Hash])
  }.as[GetHeaders]

  def command = "getheaders"
}

package io.github.yzernik.bitcoinscodec.messages

import io.github.yzernik.bitcoinscodec.structures.{Hash, Message, MessageCompanion, VarList}
import scodec.Codec
import scodec.codecs._

case class GetHeaders(
  version: Long,
  block_locator_hashes: List[Hash],
  hash_stop: Hash = Hash.NULL) extends Message {
  type E = GetHeaders
  def companion = GetHeaders
}

object GetHeaders extends MessageCompanion[GetHeaders] {
  override def codec(version: Int): Codec[GetHeaders] = {
    ("version" | uint32L) ::
      ("block_locator_hashes" | VarList(Codec[Hash])) ::
      ("hash_stop" | Codec[Hash])
  }.as[GetHeaders]
  override def command = "getheaders"
}

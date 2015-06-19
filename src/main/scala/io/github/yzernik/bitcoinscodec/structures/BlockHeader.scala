package io.github.yzernik.bitcoinscodec.structures

import scodec.Codec
import scodec.codecs._
import io.github.yzernik.bitcoinscodec.util.Util

case class BlockHeader(
  version: Long,
  prev_block: Hash,
  merkle_root: Hash,
  timestamp: Long,
  bits: Long,
  nonce: Long) {

  def hash: Hash = {
    val bytes = BlockHeader.codec.encode(this).toOption.get
    Util.hash(bytes.toByteArray)
  }

}

object BlockHeader {

  implicit val codec: Codec[BlockHeader] = {
    ("version" | uint32L) ::
      ("prev_block" | Codec[Hash]) ::
      ("merkle_root" | Codec[Hash]) ::
      ("timestamp" | uint32L) ::
      ("bits" | uint32L) ::
      ("nonce" | uint32L)
  }.as[BlockHeader]

}

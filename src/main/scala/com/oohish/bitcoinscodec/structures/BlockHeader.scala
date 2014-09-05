package com.oohish.bitcoinscodec.structures

import scodec.Codec
import scodec.codecs._

case class BlockHeader(
  version: Long,
  prev_block: Hash,
  merkle_root: Hash,
  timestamp: Long,
  bits: Long,
  nonce: Long)

object BlockHeader {

  implicit val codec: Codec[BlockHeader] = {
    ("version" | uint32) ::
      ("prev_block" | Codec[Hash]) ::
      ("merkle_root" | Codec[Hash]) ::
      ("timestamp" | uint32) ::
      ("bits" | uint32) ::
      ("nonce" | uint32)
  }.as[BlockHeader]

}
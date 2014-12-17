package com.github.yzernik.bitcoinscodec.messages

import com.github.yzernik.bitcoinscodec.structures.Message
import com.github.yzernik.bitcoinscodec.structures.MessageCompanion
import com.github.yzernik.bitcoinscodec.structures.NetworkAddress
import com.github.yzernik.bitcoinscodec.structures.UInt64.bigIntCodec
import com.github.yzernik.bitcoinscodec.structures.VarStr

import scodec.Codec
import scodec.HListCodecEnrichedWithHListSupport
import scodec.ValueCodecEnrichedWithHListSupport
import scodec.codecs.StringEnrichedWithCodecNamingSupport
import scodec.codecs.int32L
import scodec.codecs.int64L
import scodec.codecs.mappedEnum
import scodec.codecs.uint8

case class Version(
  version: Int,
  services: BigInt,
  timestamp: Long,
  addr_recv: NetworkAddress,
  addr_from: NetworkAddress,
  nonce: BigInt,
  user_agent: String,
  start_height: Int,
  relay: Boolean) extends Message {
  type E = Version
  def companion = Version
}

object Version extends MessageCompanion[Version] {
  def codec(version: Int): Codec[Version] = {
    ("version" | int32L) ::
      ("services" | Codec[BigInt]) ::
      ("timestamp" | int64L) ::
      ("addr_recv" | Codec[NetworkAddress]) ::
      ("addr_from" | Codec[NetworkAddress]) ::
      ("nonce" | Codec[BigInt]) ::
      ("user_agent" | VarStr.codec) ::
      ("start_height" | int32L) ::
      ("relay" | mappedEnum(uint8, false -> 0, true -> 1))
  }.as[Version]
  def command = "version"
}
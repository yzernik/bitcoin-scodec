package com.oohish.bitcoinscodec.messages

import scodec.Codec
import scodec.codecs._
import com.oohish.bitcoinscodec.structures.UInt64
import com.oohish.bitcoinscodec.structures.VarStr
import com.oohish.bitcoinscodec.structures.Message.Message
import com.oohish.bitcoinscodec.structures.NetworkAddress

case class Version(
  version: Int,
  services: UInt64,
  timestamp: Long,
  addr_recv: NetworkAddress,
  addr_from: NetworkAddress,
  nonce: UInt64,
  user_agent: String,
  start_height: Int,
  relay: Boolean) extends Message {
  type E = Version
  def codec = Version.codec
}

object Version {

  /** Creates a Version. */
  def apply(
    version: Int,
    services: BigInt,
    timestamp: Long,
    addr_recv: NetworkAddress,
    addr_from: NetworkAddress,
    nonce: BigInt,
    user_agent: String,
    start_height: Int,
    relay: Boolean): Version =
    Version(
      version,
      UInt64(UInt64.bigIntToLong(services)),
      timestamp,
      addr_recv,
      addr_from,
      UInt64(UInt64.bigIntToLong(nonce)),
      user_agent,
      start_height,
      relay)

  val codec: Codec[Version] = {
    ("version" | int32L) ::
      ("services" | Codec[UInt64]) ::
      ("timestamp" | int64L) ::
      ("addr_recv" | Codec[NetworkAddress]) ::
      ("addr_from" | Codec[NetworkAddress]) ::
      ("nonce" | Codec[UInt64]) ::
      ("user_agent" | VarStr.codec) ::
      ("start_height" | int32L) ::
      ("relay" | mappedEnum(uint8, false -> 0, true -> 1))
  }.as[Version]
}
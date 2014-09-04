package com.oohish.bitcoinscodec.messages

import scodec.Codec
import scodec.codecs._
import com.oohish.bitcoinscodec.structures.UInt64
import com.oohish.bitcoinscodec.structures.VarStr

case class Version(
  version: Int,
  services: UInt64,
  timestamp: Long,
  addr_recv: NetworkAddress,
  addr_from: NetworkAddress,
  nonce: UInt64,
  user_agent: String,
  start_height: Int,
  relay: Option[Boolean])

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
    relay: Option[Boolean]): Version =
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

  implicit val codec: Codec[Version] =
    {
      ("version" | int32L) ::
        ("services" | Codec[UInt64]) ::
        ("timestamp" | int64L) ::
        ("addr_recv" | Codec[NetworkAddress]) ::
        ("addr_from" | Codec[NetworkAddress]) ::
        ("nonce" | Codec[UInt64]) ::
        ("user_agent" | VarStr.codec) ::
        ("start_height" | int32L) ::
        ("relay" | conditional(false, bool))
    }.as[Version]

  val codecWithRelay: Codec[Version] =
    {
      ("version" | int32L) ::
        ("services" | Codec[UInt64]) ::
        ("timestamp" | int64L) ::
        ("addr_recv" | Codec[NetworkAddress]) ::
        ("addr_from" | Codec[NetworkAddress]) ::
        ("nonce" | Codec[UInt64]) ::
        ("user_agent" | VarStr.codec) ::
        ("start_height" | int32L) ::
        ("relay" | conditional(true, bool))
    }.as[Version]
}
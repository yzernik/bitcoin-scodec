package io.github.yzernik.bitcoinscodec.messages

import io.github.yzernik.bitcoinscodec.structures._
import scodec.Codec
import scodec.codecs._

case class Version(
  version: Int,
  services: Version.SERVICES,
  timestamp: Long,
  addr_recv: NetworkAddress,
  addr_from: NetworkAddress,
  nonce: UInt64,
  user_agent: String,
  start_height: Int,
  relay: Boolean) extends Message {
  type E = Version
  def companion = Version
}

object Version extends MessageCompanion[Version] {

  sealed trait SERVICES
  case object NODE_NETWORK extends SERVICES
  case object NODE_GETUTXO extends SERVICES
  case object NODE_BLOOM extends SERVICES
  case object NODE_WITNESS extends SERVICES
  case object NODE_NETWORK_LIMITED extends SERVICES

  implicit val servicesCodec: Codec[SERVICES] = mappedEnum(Codec[UInt64],
    NODE_NETWORK -> UInt64(1L),
    NODE_GETUTXO -> UInt64(2L),
    NODE_BLOOM -> UInt64(4L),
    NODE_WITNESS -> UInt64(8L),
    NODE_NETWORK_LIMITED -> UInt64(1024L))

  override def codec(version: Int): Codec[Version] = {
    ("version" | int32L) >>:~ { verNum =>
      ("services" | Codec[SERVICES]) ::
        ("timestamp" | int64L) ::
        ("addr_recv" | Codec[NetworkAddress]) ::
        ("addr_from" | Codec[NetworkAddress]) ::
        ("nonce" | Codec[UInt64]) ::
        ("user_agent" | VarStr()) ::
        ("start_height" | int32L) ::
        ("relay" | withDefault(conditional(verNum >= 70001, relayCodec), provide(true)))
    }
  }.as[Version]

  val relayCodec: Codec[Boolean] = {
    ("relay" | mappedEnum(uint8, false -> 0, true -> 1))
  }.as[Boolean]

  override def command = "version"
}

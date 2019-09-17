package io.github.yzernik.bitcoinscodec.messages

import io.github.yzernik.bitcoinscodec.structures.{Message, MessageCompanion, NetworkAddress, UInt64, VarStr}
import scodec.Codec
import scodec.codecs._

import scala.util.Random

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
  def companion = Version
}

object Version extends MessageCompanion[Version] {
  override def codec(version: Int): Codec[Version] = {
    ("version" | int32L) >>:~ { verNum =>
      ("services" | Codec[UInt64]) ::
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

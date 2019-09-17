package io.github.yzernik.bitcoinscodec.messages

import io.github.yzernik.bitcoinscodec.structures.Message
import io.github.yzernik.bitcoinscodec.structures.MessageCompanion
import io.github.yzernik.bitcoinscodec.structures.NetworkAddress
import io.github.yzernik.bitcoinscodec.structures.UInt64.bigIntCodec
import io.github.yzernik.bitcoinscodec.structures.VarStr
import scodec.Codec
import scodec.codecs._
import scala.util.Random

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
    ("version" | int32L) >>:~ { verNum =>
      ("services" | Codec[BigInt]) ::
        ("timestamp" | int64L) ::
        ("addr_recv" | Codec[NetworkAddress]) ::
        ("addr_from" | Codec[NetworkAddress]) ::
        ("nonce" | Codec[BigInt]) ::
        ("user_agent" | VarStr()) ::
        ("start_height" | int32L) ::
        ("relay" | withDefault(conditional(verNum >= 70001, relayCodec), provide(true)))
    }
  }.as[Version]

  val relayCodec: Codec[Boolean] = {
    ("relay" | mappedEnum(uint8, false -> 0, true -> 1))
  }.as[Boolean]

  def command = "version"

  def genNonce: BigInt = BigInt(Random.nextLong()) + Long.MaxValue + 1
}

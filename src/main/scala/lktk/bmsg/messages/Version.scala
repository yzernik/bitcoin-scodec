package lktk.bmsg.messages

import lktk.bmsg.structures._
import lktk.bmsg.structures.UInt64.bigIntCodec
import lktk.bmsg.util.ProtocolVersion

import scodec.Codec
import scodec.codecs._

case class Version(
  version: Int,
  services: BigInt,
  timestamp: Long,
  addrRecv: NetworkAddress,
  addrFrom: NetworkAddress,
  nonce: BigInt,
  userAgent: String,
  startHeight: Int,
  relay: Boolean) extends Message {
  type E = Version
  def companion = Version
}

object Version extends MessageCompanion[Version] {
  def codec(version: Int): Codec[Version] = {
    ("version" | int32L) >>:~ { verNum =>
      ("services" | Codec[BigInt]) ::
        ("timestamp" | int64L) ::
        ("addrRecv" | Codec[NetworkAddress]) ::
        ("addrFrom" | Codec[NetworkAddress]) ::
        ("nonce" | Codec[BigInt]) ::
        ("userAgent" | VarStr.codec) ::
        ("startHeight" | int32L) ::
        ("relay" | withDefaultValue(conditional(verNum >= ProtocolVersion.bloomVersion, relayCodec), true)) //BIP37
    }
  }.as[Version]

  val relayCodec: Codec[Boolean] = {
    ("relay" | mappedEnum(uint8, false -> 0, true -> 1))
  }.as[Boolean]

  def command = "version"

  def genNonce = UInt64.genRng

}

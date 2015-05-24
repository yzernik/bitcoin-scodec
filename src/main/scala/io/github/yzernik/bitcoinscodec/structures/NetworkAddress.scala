package io.github.yzernik.bitcoinscodec.structures

import java.net.InetAddress
import java.net.InetSocketAddress

import io.github.yzernik.bitcoinscodec.structures.UInt64.bigIntCodec

import scodec.Codec
import scodec.ValueCodecEnrichedWithHListSupport
import scodec.bits.BitVector
import scodec.bits.ByteVector
import scodec.bits.HexStringSyntax
import scodec.codecs.bytes
import scodec.codecs.uint16
import scodec.codecs._

case class NetworkAddress(
  services: BigInt,
  address: InetSocketAddress)

object NetworkAddress {
  val ipv4Pad = hex"00 00 00 00 00 00 00 00 00 00 FF FF"

  implicit val inetAddress: Codec[InetAddress] = {
    bytes(4).xmap(bits => InetAddress.getByAddress(bits.toArray),
      f => ByteVector(f.getAddress))
  }

  implicit val inetSocketAddress: Codec[InetSocketAddress] = {
    ("services" | Codec[InetAddress]) ::
      ("port" | uint16)
  }.as[(InetAddress, Int)]
    .xmap(a => new InetSocketAddress(a._1, a._2), isa => (isa.getAddress(), isa.getPort()))

  implicit val codec: Codec[NetworkAddress] = {
    ("services" | Codec[BigInt]) ::
      ("address" | Codec[InetSocketAddress])
  }.as[NetworkAddress]

}

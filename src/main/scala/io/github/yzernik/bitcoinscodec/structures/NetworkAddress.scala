package io.github.yzernik.bitcoinscodec.structures

import java.net.InetAddress
import java.net.InetSocketAddress

import io.github.yzernik.bitcoinscodec.structures.UInt64.bigIntCodec

import scodec.Codec
import scodec.DecodeResult
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

  implicit val inetAddress = Codec[InetAddress](
    (ia: InetAddress) => {
      val bts = ByteVector(ia.getAddress())
      if (bts.length == 4) {
        bytes(16).encode(ipv4Pad ++ bts)
      } else {
        bytes(16).encode(bts)
      }
    },
    (buf: BitVector) => bytes(16).decode(buf).map {
      case b =>
        val bts = if (b.value.take(12) == ipv4Pad) {
          b.value.drop(12)
        } else {
          b.value
        }
        DecodeResult(InetAddress.getByAddress(bts.toArray), b.remainder)
    })

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

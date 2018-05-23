package lktk.bmsg.structures

import java.net.{InetAddress, InetSocketAddress}

import UInt64.bigIntCodec

import scodec.{Codec, DecodeResult, ValueCodecEnrichedWithHListSupport}
import scodec.bits.{HexStringSyntax, BitVector, ByteVector}
import scodec.codecs.{bytes, uint16}
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
    ("host" | Codec[InetAddress]) ::
      ("port" | uint16)
  }.as[(InetAddress, Int)]
    .xmap({ case (host, port) => new InetSocketAddress(host, port) },
      isa => (isa.getAddress(), isa.getPort()))

  implicit val codec: Codec[NetworkAddress] = {
    ("services" | Codec[BigInt]) ::
      ("address" | Codec[InetSocketAddress])
  }.as[NetworkAddress]

}

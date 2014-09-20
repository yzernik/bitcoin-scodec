package com.oohish.bitcoinscodec.structures

import scalaz.-\/
import scalaz.\/-
import scodec.Codec
import scodec.codecs._
import scodec.bits._
import scala.Left
import scala.Right
import scodec.HListCodecEnrichedWithHListSupport
import scodec.ValueCodecEnrichedWithHListSupport
import java.net.InetAddress
import java.net.InetSocketAddress

case class NetworkAddress(
  services: UInt64,
  address: InetSocketAddress)

object NetworkAddress {

  /** Creates a NetworkAddress. */
  def apply(
    services: BigInt,
    address: InetSocketAddress): NetworkAddress =
    NetworkAddress(UInt64(UInt64.bigIntToLong(services)), address)

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
      case (a, b) =>
        val bts = if (b.take(12) == ipv4Pad) {
          b.drop(12)
        } else {
          b
        }
        (a, InetAddress.getByAddress(bts.toArray))
    })

  implicit val inetSocketAddress: Codec[InetSocketAddress] = {
    ("services" | Codec[InetAddress]) ::
      ("port" | uint16)
  }.as[(InetAddress, Int)]
    .xmap(a => new InetSocketAddress(a._1, a._2), isa => (isa.getAddress(), isa.getPort()))

  implicit val codec: Codec[NetworkAddress] = {
    ("services" | Codec[UInt64]) ::
      ("address" | Codec[InetSocketAddress])
  }.as[NetworkAddress]

}

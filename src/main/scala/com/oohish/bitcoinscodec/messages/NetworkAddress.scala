package com.oohish.bitcoinscodec.messages

import scalaz.{ \/, \/-, -\/, Monad, Monoid }

import scodec.Codec
import scodec.codecs._
import scodec.bits._

case class NetworkAddress(
  services: Long,
  address: Either[IPV4, IPV6],
  port: Port)

object NetworkAddress {

  val ipv4Pad = hex"00 00 00 00 00 00 00 00 00 00 FF FF".toBitVector

  val ipCodec = Codec[Either[IPV4, IPV6]](
    (ip: Either[IPV4, IPV6]) => ip.fold(ipv4 => {
      for {
        pad <- constant(ipv4Pad).encode(())
        addr <- IPV4.codec.encode(ipv4)
      } yield pad ++ addr
    }, ipv6 => IPV6.codec.encode(ipv6)),
    (buf: BitVector) => constant(ipv4Pad).decode(buf) match {
      case \/-((rest, _)) =>
        IPV4.codec.decode(rest).map { case (a, b) => (a, Left(b)) }
      case -\/(err) =>
        IPV6.codec.decode(buf).map { case (a, b) => (a, Right(b)) }
    })

  implicit val codec: Codec[NetworkAddress] = (int64L :: ipCodec :: Codec[Port]).as[NetworkAddress]
}

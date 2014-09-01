package com.oohish.bitcoinz.messages

import scodec.Codec
import scodec.codecs._
import scodec.bits.ByteVector
import scodec.bits.BitVector

case class NetworkAddress(
  time: Long,
  services: Long,
  address: IPV4,
  port: Port)

object NetworkAddress {
  implicit val codec: Codec[NetworkAddress] = (uint32 :: uint32 :: Codec[IPV4] :: Codec[Port]).as[NetworkAddress]
}

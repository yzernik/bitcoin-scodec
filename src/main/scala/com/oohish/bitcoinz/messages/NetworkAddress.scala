package com.oohish.bitcoinz.messages

import scodec.Codec
import scodec.codecs._
import scodec.bits.ByteVector
import scodec.bits.BitVector

case class NetworkAddress(
  services: Long,
  address: IPV4,
  port: Port)

object NetworkAddress {
  implicit val codec: Codec[NetworkAddress] = (int64L :: Codec[IPV4] :: Codec[Port]).as[NetworkAddress]
}

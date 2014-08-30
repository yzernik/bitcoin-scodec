package com.oohish.bitcoinz.messages

import scodec.Codec
import scodec.codecs._

case class NetworkAddress(
  time: Long,
  services: Long,
  // TODO: ip
  port: Port)

object NetworkAddress {
  implicit val codec: Codec[NetworkAddress] =
    (uint32 :: uint32 :: Codec[Port]).as[NetworkAddress]
}

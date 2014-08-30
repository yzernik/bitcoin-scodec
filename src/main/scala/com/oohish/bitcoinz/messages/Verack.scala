package com.oohish.bitcoinz.messages

import scodec.Codec
import scodec.codecs._
import scodec.bits.ByteVector

case class Verack()

object Verack {
  implicit val codec: Codec[Verack] = provide(Verack())
}
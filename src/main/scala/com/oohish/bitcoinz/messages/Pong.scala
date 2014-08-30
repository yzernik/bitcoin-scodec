package com.oohish.bitcoinz.messages

import scodec.Codec
import scodec.codecs._

case class Pong(value: Long)

object Pong {
  implicit val codec: Codec[Pong] = int64.xmap(Pong.apply, _.value)
}
package com.oohish.bitcoinscodec.messages

import scodec.Codec
import scodec.codecs._

case class Ping(value: Long)

object Ping {
  implicit val codec: Codec[Ping] = int64.xmap(Ping.apply, _.value)
}
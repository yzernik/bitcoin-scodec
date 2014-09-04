package com.oohish.bitcoinscodec.messages

import scodec.Codec
import scodec.codecs._
import com.oohish.bitcoinscodec.structures.UInt64

case class Ping(value: UInt64)

object Ping {

  /** Creates a Ping. */
  def apply(value: BigInt): Ping =
    Ping(UInt64(UInt64.bigIntToLong(value)))

  implicit val codec: Codec[Ping] =
    Codec[UInt64].xmap(Ping.apply, _.value)
}
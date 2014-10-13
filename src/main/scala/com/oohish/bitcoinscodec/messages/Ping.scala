package com.oohish.bitcoinscodec.messages

import scodec.Codec
import scodec.codecs._
import com.oohish.bitcoinscodec.structures.UInt64
import com.oohish.bitcoinscodec.structures.Message
import com.oohish.bitcoinscodec.structures.MessageCompanion

case class Ping(value: UInt64) extends Message {
  type E = Ping
  def companion = Ping
}

object Ping extends MessageCompanion[Ping] {
  /** Creates a Ping. */
  def apply(value: BigInt): Ping =
    Ping(UInt64(UInt64.bigIntToLong(value)))

  def codec(version: Int): Codec[Ping] =
    Codec[UInt64].xmap(Ping.apply, _.value)

  def command = "ping"
}
package com.oohish.bitcoinscodec.messages

import com.oohish.bitcoinscodec.structures.Message
import com.oohish.bitcoinscodec.structures.MessageCompanion
import com.oohish.bitcoinscodec.structures.UInt64.bigIntCodec

import scodec.Codec

case class Ping(value: BigInt) extends Message {
  type E = Ping
  def companion = Ping
}

object Ping extends MessageCompanion[Ping] {
  def codec(version: Int): Codec[Ping] =
    Codec[BigInt].xmap(Ping.apply, _.value)
  def command = "ping"
}
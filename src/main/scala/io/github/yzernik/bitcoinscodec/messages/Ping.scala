package io.github.yzernik.bitcoinscodec.messages

import io.github.yzernik.bitcoinscodec.structures.{Message, MessageCompanion, UInt64}
import scodec.Codec

case class Ping(nonce: UInt64) extends Message {
  type E = Ping
  def companion = Ping
}

object Ping extends MessageCompanion[Ping] {
  def codec(version: Int): Codec[Ping] =
    Codec[UInt64].xmap(Ping.apply, _.nonce)
  def command = "ping"
}
package io.github.yzernik.bitcoinscodec.messages

import io.github.yzernik.bitcoinscodec.structures.{Message, MessageCompanion, UInt64}
import io.github.yzernik.bitcoinscodec.util.Util
import scodec.Codec

case class Ping(nonce: UInt64) extends Message {
  type E = Ping
  override def companion = Ping
}

object Ping extends MessageCompanion[Ping] {
  override def codec(version: Int): Codec[Ping] =
    Codec[UInt64].xmap(Ping.apply, _.nonce)
  override def command = "ping"

  def generate =
    Ping(Util.generateNonce64)
}
package io.github.yzernik.bitcoinscodec.messages

import io.github.yzernik.bitcoinscodec.structures.{Message, MessageCompanion, UInt64}
import scodec.Codec

case class Pong(nonce: UInt64) extends Message {
  type E = Pong
  def companion = Pong
}

object Pong extends MessageCompanion[Pong] {
  def codec(version: Int): Codec[Pong] =
    Codec[UInt64].xmap(Pong.apply, _.nonce)
  def command = "pong"
}

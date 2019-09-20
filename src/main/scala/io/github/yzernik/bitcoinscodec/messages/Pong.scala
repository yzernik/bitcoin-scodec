package io.github.yzernik.bitcoinscodec.messages

import io.github.yzernik.bitcoinscodec.structures.{Message, MessageCompanion, UInt64}
import io.github.yzernik.bitcoinscodec.util.Util
import scodec.Codec

case class Pong(nonce: UInt64) extends Message {
  type E = Pong
  override def companion = Pong
}

object Pong extends MessageCompanion[Pong] {
  override def codec(version: Int): Codec[Pong] =
    Codec[UInt64].xmap(Pong.apply, _.nonce)
  override def command = "pong"

  def generate =
    Pong(Util.generateNonce64)
}

package com.github.yzernik.bitcoinscodec.messages

import com.github.yzernik.bitcoinscodec.structures.Message
import com.github.yzernik.bitcoinscodec.structures.MessageCompanion
import com.github.yzernik.bitcoinscodec.structures.UInt64.bigIntCodec

import scodec.Codec

case class Pong(value: BigInt) extends Message {
  type E = Pong
  def companion = Pong
}

object Pong extends MessageCompanion[Pong] {
  def codec(version: Int): Codec[Pong] =
    Codec[BigInt].xmap(Pong.apply, _.value)
  def command = "pong"
}

package com.oohish.bitcoinscodec.messages

import com.oohish.bitcoinscodec.structures.Message
import com.oohish.bitcoinscodec.structures.MessageCompanion
import com.oohish.bitcoinscodec.structures.UInt64.bigIntCodec

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
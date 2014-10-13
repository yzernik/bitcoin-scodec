package com.oohish.bitcoinscodec.messages

import scodec.Codec
import scodec.codecs._
import com.oohish.bitcoinscodec.structures.UInt64
import com.oohish.bitcoinscodec.structures.Message
import com.oohish.bitcoinscodec.structures.MessageCompanion

case class Pong(value: UInt64) extends Message {
  type E = Pong
  def companion = Pong
}

object Pong extends MessageCompanion[Pong] {
  /** Creates a Pong. */
  def apply(value: BigInt): Pong =
    Pong(UInt64(UInt64.bigIntToLong(value)))

  def codec(version: Int): Codec[Pong] =
    Codec[UInt64].xmap(Pong.apply, _.value)
  def command = "pong"
}
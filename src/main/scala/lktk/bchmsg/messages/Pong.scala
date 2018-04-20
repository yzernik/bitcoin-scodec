package lktk.bchmsg.messages

import lktk.bchmsg.structures.{Message, MessageCompanion}

import lktk.bchmsg.structures.UInt64.bigIntCodec
import scodec.Codec

case class Pong(nonce: BigInt) extends Message {
  type E = Pong
  def companion = Pong
}

object Pong extends MessageCompanion[Pong] {
  def codec(version: Int): Codec[Pong] =
    Codec[BigInt].xmap(Pong.apply, _.nonce)
  def command = "pong"
}

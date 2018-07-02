package lktk.bmsg.messages

import lktk.bmsg.structures.{Message, MessageCompanion, UInt64}
import lktk.bmsg.structures.UInt64.uint64L
import scodec.Codec

case class Pong(nonce: BigInt) extends Message {
  type E = Pong
  def companion = Pong
}

object Pong extends MessageCompanion[Pong] {
  def codec(version: Int): Codec[Pong] = uint64L.xmap(Pong.apply, _.nonce)

  def command = "pong"

  def generate = Pong(UInt64.genRandom)
}

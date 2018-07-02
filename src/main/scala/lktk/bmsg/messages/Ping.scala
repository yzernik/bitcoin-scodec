package lktk.bmsg.messages

import lktk.bmsg.structures.{Message, MessageCompanion, UInt64}
import lktk.bmsg.structures.UInt64.uint64L
import scodec.Codec

case class Ping(nonce: BigInt) extends Message {
  type E = Ping
  def companion = Ping
}

object Ping extends MessageCompanion[Ping] {
  def codec(version: Int): Codec[Ping] = uint64L.xmap(Ping.apply, _.nonce)

  def command = "ping"

  def genPing = Ping(UInt64.genRandom)
}
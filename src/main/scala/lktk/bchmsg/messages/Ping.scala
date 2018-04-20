package lktk.bchmsg.messages

import lktk.bchmsg.structures.Message
import lktk.bchmsg.structures.MessageCompanion
import lktk.bchmsg.structures.UInt64.bigIntCodec
import lktk.bchmsg.structures.{Message, MessageCompanion}

case class Ping(nonce: BigInt) extends Message {
  type E = Ping
  def companion = Ping
}

object Ping extends MessageCompanion[Ping] {
  def codec(version: Int): Codec[Ping] =
    Codec[BigInt].xmap(Ping.apply, _.nonce)
  def command = "ping"
}
package lktk.bchmsg.messages

import lktk.bchmsg.structures.Message
import lktk.bchmsg.structures.MessageCompanion
import lktk.bchmsg.structures.{Message, MessageCompanion}

case class MemPool() extends Message {
  type E = MemPool
  def companion = MemPool
}

object MemPool extends MessageCompanion[MemPool] {
  def codec(version: Int): Codec[MemPool] = provide(MemPool())
  def command = "mempool"
}
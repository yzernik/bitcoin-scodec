package io.github.yzernik.bitcoinscodec.messages

import io.github.yzernik.bitcoinscodec.structures.{Message, MessageCompanion}
import scodec.Codec
import scodec.codecs.provide

case class MemPool() extends Message {
  type E = MemPool
  override def companion = MemPool
}

object MemPool extends MessageCompanion[MemPool] {
  override def codec(version: Int): Codec[MemPool] = provide(MemPool())
  override def command = "mempool"
}
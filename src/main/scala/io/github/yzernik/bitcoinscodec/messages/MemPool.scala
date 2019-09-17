package io.github.yzernik.bitcoinscodec.messages

import io.github.yzernik.bitcoinscodec.structures.Message
import io.github.yzernik.bitcoinscodec.structures.MessageCompanion

import scodec.Codec
import scodec.codecs.provide

case class MemPool() extends Message {
  type E = MemPool
  def companion = MemPool
}

object MemPool extends MessageCompanion[MemPool] {
  override def codec(version: Int): Codec[MemPool] = provide(MemPool())
  override def command = "mempool"
}
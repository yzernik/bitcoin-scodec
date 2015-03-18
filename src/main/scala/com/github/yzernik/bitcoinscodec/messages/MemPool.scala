package com.github.yzernik.bitcoinscodec.messages

import com.github.yzernik.bitcoinscodec.structures.Message
import com.github.yzernik.bitcoinscodec.structures.MessageCompanion

import scodec.Codec
import scodec.codecs.provide

case class MemPool() extends Message {
  type E = MemPool
  def companion = MemPool
}

object MemPool extends MessageCompanion[MemPool] {
  def codec(version: Int): Codec[MemPool] = provide(MemPool())
  def command = "mempool"
}
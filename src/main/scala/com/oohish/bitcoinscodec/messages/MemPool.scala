package com.oohish.bitcoinscodec.messages

import scodec.Codec
import scodec.codecs._
import scodec.bits.ByteVector
import com.oohish.bitcoinscodec.structures.Message

case class MemPool() extends Message {
  type E = MemPool
  def codec = MemPool.codec
  def command = "mempool"
}

object MemPool {
  implicit val codec: Codec[MemPool] = provide(MemPool())
}
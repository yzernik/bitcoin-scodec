package com.oohish.bitcoinscodec.messages

import scodec.Codec
import scodec.codecs._
import scodec.bits.ByteVector
import com.oohish.bitcoinscodec.structures.Message.Message

case class MemPool() extends Message {
  type E = MemPool
  def codec = MemPool.codec
}

object MemPool {
  implicit val codec: Codec[MemPool] = provide(MemPool())
}
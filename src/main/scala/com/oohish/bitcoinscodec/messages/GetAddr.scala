package com.oohish.bitcoinscodec.messages

import scodec.Codec
import scodec.codecs._
import scodec.bits.ByteVector
import com.oohish.bitcoinscodec.structures.Message.Message

case class GetAddr() extends Message {
  type E = GetAddr
  def codec = GetAddr.codec
}

object GetAddr {
  implicit val codec: Codec[GetAddr] = provide(GetAddr())
}
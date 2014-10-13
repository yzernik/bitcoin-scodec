package com.oohish.bitcoinscodec.messages

import scodec.bits.ByteVector
import scodec.Codec
import scodec.codecs
import scalaz.std.anyVal.unitInstance
import scodec.bits.BitVector
import scodec.Codec
import scodec.codecs._
import shapeless._
import com.oohish.bitcoinscodec.structures._
import com.oohish.bitcoinscodec.structures.Message

case class Headers(invs: List[Block]) extends Message {
  type E = Headers
  def companion = Headers
}

object Headers extends MessageCompanion[Headers] {
  def codec(version: Int): Codec[Headers] =
    VarList.varList(Block.codec(version)).as[Headers]
  def command = "headers"
}
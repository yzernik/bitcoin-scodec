package com.github.yzernik.bitcoinscodec.messages

import scodec.bits.ByteVector
import scodec.Codec
import scodec.codecs
import scalaz.std.anyVal.unitInstance
import scodec.bits.BitVector
import scodec.Codec
import scodec.codecs._
import shapeless._
import com.github.yzernik.bitcoinscodec.structures.VarList
import com.github.yzernik.bitcoinscodec.structures.Message
import com.github.yzernik.bitcoinscodec.structures.InvVect
import com.github.yzernik.bitcoinscodec.structures.MessageCompanion

case class NotFound(invs: List[InvVect]) extends Message {
  type E = NotFound
  def companion = NotFound
}

object NotFound extends MessageCompanion[NotFound] {
  def codec(version: Int): Codec[NotFound] =
    VarList.varList(Codec[InvVect]).as[NotFound]
  def command = "notfound"
}
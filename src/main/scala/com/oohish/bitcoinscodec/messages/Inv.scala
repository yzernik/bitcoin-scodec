package com.oohish.bitcoinscodec.messages

import scodec.bits.ByteVector
import scodec.Codec
import scodec.codecs
import scalaz.std.anyVal.unitInstance
import scodec.bits.BitVector
import scodec.Codec
import scodec.codecs._
import shapeless._
import com.oohish.bitcoinscodec.structures.VarList
import com.oohish.bitcoinscodec.structures.Message
import com.oohish.bitcoinscodec.structures.InvVect
import com.oohish.bitcoinscodec.structures.MessageCompanion

case class Inv(invs: List[InvVect]) extends Message {
  type E = Inv
  def companion = Inv
}

object Inv extends MessageCompanion[Inv] {
  def codec(version: Int): Codec[Inv] =
    VarList.varList(Codec[InvVect]).as[Inv]
  def command = "inv"
}
package io.github.yzernik.bitcoinscodec.messages

import io.github.yzernik.bitcoinscodec.structures.InvVect
import io.github.yzernik.bitcoinscodec.structures.Message
import io.github.yzernik.bitcoinscodec.structures.MessageCompanion
import io.github.yzernik.bitcoinscodec.structures.VarList

import scodec.Codec

case class Inv(invs: List[InvVect]) extends Message {
  type E = Inv
  def companion = Inv
}

object Inv extends MessageCompanion[Inv] {
  def codec(version: Int): Codec[Inv] =
    VarList.varList(Codec[InvVect]).as[Inv]
  def command = "inv"
}
package com.github.yzernik.bitcoinscodec.messages

import com.github.yzernik.bitcoinscodec.structures.InvVect
import com.github.yzernik.bitcoinscodec.structures.Message
import com.github.yzernik.bitcoinscodec.structures.MessageCompanion
import com.github.yzernik.bitcoinscodec.structures.VarList

import scodec.Codec

case class NotFound(invs: List[InvVect]) extends Message {
  type E = NotFound
  def companion = NotFound
}

object NotFound extends MessageCompanion[NotFound] {
  def codec(version: Int): Codec[NotFound] =
    VarList.varList(Codec[InvVect]).as[NotFound]
  def command = "notfound"
}
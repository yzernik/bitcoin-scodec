package com.github.yzernik.bitcoinscodec.messages

import com.github.yzernik.bitcoinscodec.structures.Message
import com.github.yzernik.bitcoinscodec.structures.MessageCompanion
import com.github.yzernik.bitcoinscodec.structures.NetworkAddress
import com.github.yzernik.bitcoinscodec.structures.VarList

import scodec.Codec
import scodec.ValueCodecEnrichedWithHListSupport
import scodec.codecs.StringEnrichedWithCodecNamingSupport
import scodec.codecs.uint32L

case class Addr(addrs: List[(Long, NetworkAddress)]) extends Message {
  type E = Addr
  def companion = Addr
}

case object Addr extends MessageCompanion[Addr] {
  val timeAddr = {
    ("time" | uint32L) ::
      ("net_addr" | Codec[NetworkAddress])
  }.as[(Long, NetworkAddress)]
  def codec(version: Int): Codec[Addr] =
    VarList.varList(timeAddr).as[Addr]
  val command = "addr"
}

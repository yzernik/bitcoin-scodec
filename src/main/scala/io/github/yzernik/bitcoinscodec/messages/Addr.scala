package io.github.yzernik.bitcoinscodec.messages

import io.github.yzernik.bitcoinscodec.structures.Message
import io.github.yzernik.bitcoinscodec.structures.MessageCompanion
import io.github.yzernik.bitcoinscodec.structures.NetworkAddress
import io.github.yzernik.bitcoinscodec.structures.VarList

import scodec.Codec
import scodec.codecs.uint32L
import scodec.codecs._

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

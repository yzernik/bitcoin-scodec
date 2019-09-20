package io.github.yzernik.bitcoinscodec.messages

import io.github.yzernik.bitcoinscodec.structures.{Message, MessageCompanion, NetworkAddress, VarList}
import scodec.Codec
import scodec.codecs._

case class Addr(addrs: List[(Long, NetworkAddress)]) extends Message {
  type E = Addr
  override def companion = Addr
}

object Addr extends MessageCompanion[Addr] {
  val timeAddr = {
    ("time" | uint32L) ::
      ("net_addr" | Codec[NetworkAddress])
  }.as[(Long, NetworkAddress)]
  override def codec(version: Int): Codec[Addr] =
    VarList(timeAddr).as[Addr]
  override val command = "addr"
}

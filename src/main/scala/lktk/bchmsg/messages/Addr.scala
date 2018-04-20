package lktk.bchmsg.messages

import lktk.bchmsg.structures.Message
import lktk.bchmsg.structures.MessageCompanion
import lktk.bchmsg.structures.NetworkAddress
import lktk.bchmsg.structures.{Message, MessageCompanion, NetworkAddress, VarList}

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

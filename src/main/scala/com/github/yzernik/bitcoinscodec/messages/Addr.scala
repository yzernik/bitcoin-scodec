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
import com.github.yzernik.bitcoinscodec.structures.NetworkAddress
import com.github.yzernik.bitcoinscodec.structures.MessageCompanion

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
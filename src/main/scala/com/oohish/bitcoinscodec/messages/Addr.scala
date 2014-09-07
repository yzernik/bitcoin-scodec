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
import com.oohish.bitcoinscodec.structures.Message.Message

case class Addr(addrs: List[(Long, NetworkAddress)]) extends Message {
  type E = Addr
  def codec = Addr.codec
}

object Addr {
  import VarList._

  val timeAddr = {
    ("time" | uint32L) ::
      ("net_addr" | Codec[NetworkAddress])
  }.as[(Long, NetworkAddress)]

  implicit val codec: Codec[Addr] =
    VarList.varList(timeAddr).as[Addr]

}
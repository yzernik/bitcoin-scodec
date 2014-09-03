package com.oohish.bitcoinscodec.messages

import scodec.bits.ByteVector
import scodec.Codec
import scodec.codecs
import scalaz.std.anyVal.unitInstance
import scodec.bits.BitVector
import scodec.Codec
import scodec.codecs._
import shapeless._
import com.oohish.bitcoinscodec.structures.InvVect

case class Addr(addrs: List[(Long, NetworkAddress)])

object Addr {
  import InvVect._

  implicit val codec: Codec[Addr] = {
    val timeAddrCodec: Codec[(Long, NetworkAddress)] =
      (uint32 :: Codec[NetworkAddress]).as[(Long, NetworkAddress)]
    InvVect.invVectCodec(timeAddrCodec).as[Addr]
  }
}
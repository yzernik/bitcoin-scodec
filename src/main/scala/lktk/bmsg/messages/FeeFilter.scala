package lktk.bmsg.messages

import lktk.bmsg.structures.{Message, MessageCompanion}

import lktk.bmsg.structures.UInt64.bigIntCodec
import scodec.Codec

//BIP133
case class FeeFilter(feerate: BigInt) extends Message {
  type E = FeeFilter
  def companion = FeeFilter
}

object FeeFilter extends MessageCompanion[FeeFilter] {
  def codec(version: Int): Codec[FeeFilter] =
    Codec[BigInt].xmap(FeeFilter.apply, _.feerate)

  def command = "feefilter"
}
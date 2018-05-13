package lktk.bmsg.messages

import lktk.bmsg.structures.{Message, MessageCompanion}
import lktk.bmsg.structures.UInt64.bigIntCodec

import scodec.Codec
import scodec.codecs._

//BIP152
case class SendCmpct(
  announce: Boolean,
  version: BigInt) extends Message {
  type E = SendCmpct
  def companion = SendCmpct
}

object SendCmpct extends MessageCompanion[SendCmpct] {

  def codec(version: Int): Codec[SendCmpct] = (
    ("announce" | mappedEnum(uint8, false -> 0, true -> 1)).as[Boolean] ::
      ("version" | Codec[BigInt])
  ).as[SendCmpct]
  def command = "sendcmpct"
}
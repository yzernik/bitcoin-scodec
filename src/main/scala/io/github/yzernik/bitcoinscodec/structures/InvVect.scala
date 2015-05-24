package io.github.yzernik.bitcoinscodec.structures

import scodec.Codec
import scodec.codecs._

case class InvVect(
  inv_type: InvVect.InvType,
  hash: Hash)

object InvVect {

  sealed trait InvType
  case object ERROR extends InvType
  case object MSG_TX extends InvType
  case object MSG_BLOCK extends InvType

  implicit val invTypeCodec: Codec[InvType] = mappedEnum(uint32L,
    ERROR -> 0L,
    MSG_TX -> 1L,
    MSG_BLOCK -> 2L)

  implicit val codec: Codec[InvVect] = {
    ("inv_type" | Codec[InvType]) ::
      ("addr_from" | Codec[Hash])
  }.as[InvVect]

}

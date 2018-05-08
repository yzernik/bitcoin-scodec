package lktk.bmsg.structures

import scodec.Codec
import scodec.codecs._

case class InvVect(
  inv_type: InvVect.InvType,
  hash: Hash)

object InvVect {

  trait InvType
  case object ERROR extends InvType
  case object MSG_TX extends InvType
  case object MSG_BLOCK extends InvType
  case object MSG_FILTERED_BLOCK extends InvType //TODO place holder for bip37
  case object MSG_CMPTC_BLOCK extends InvType //BIP152

  implicit val invTypeCodec: Codec[InvType] = mappedEnum(uint32L,
    ERROR -> 0L,
    MSG_TX -> 1L,
    MSG_BLOCK -> 2L,
    MSG_FILTERED_BLOCK -> 3L,
    MSG_CMPTC_BLOCK -> 4L)

  implicit val codec: Codec[InvVect] = {
    ("inv_type" | Codec[InvType]) ::
      ("addr_from" | Codec[Hash])
  }.as[InvVect]

}

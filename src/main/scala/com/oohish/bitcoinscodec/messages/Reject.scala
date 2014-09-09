package com.oohish.bitcoinscodec.messages

import scodec.bits.ByteVector
import scodec.Codec
import scodec.codecs
import scalaz.std.anyVal.unitInstance
import scodec.bits.BitVector
import scodec.Codec
import scodec.codecs._
import shapeless._
import com.oohish.bitcoinscodec.structures._
import com.oohish.bitcoinscodec.structures.Message.Message
import com.oohish.bitcoinscodec.messages.Reject.CCode

case class Reject(
  message: String,
  ccode: CCode,
  reason: String) extends Message {
  type E = Reject
  def codec = Reject.codec
}

object Reject {
  import VarList._

  sealed trait CCode
  case object REJECT_MALFORMED extends CCode
  case object REJECT_INVALID extends CCode
  case object REJECT_OBSOLETE extends CCode
  case object REJECT_DUPLICATE extends CCode
  case object REJECT_NONSTANDARD extends CCode
  case object REJECT_DUST extends CCode
  case object REJECT_INSUFFICIENTFEE extends CCode
  case object REJECT_CHECKPOINT extends CCode

  implicit val ccodeCodec: Codec[CCode] = mappedEnum(uint8,
    REJECT_MALFORMED -> 0x01,
    REJECT_INVALID -> 0x10,
    REJECT_OBSOLETE -> 0x11,
    REJECT_DUPLICATE -> 0x12,
    REJECT_NONSTANDARD -> 0x40,
    REJECT_DUST -> 0x41,
    REJECT_INSUFFICIENTFEE -> 0x42,
    REJECT_CHECKPOINT -> 0x43)

  implicit val codec: Codec[Reject] = {
    ("message" | VarStr.codec) ::
      ("ccode" | ccodeCodec) ::
      ("reason" | VarStr.codec)
  }.as[Reject]

}
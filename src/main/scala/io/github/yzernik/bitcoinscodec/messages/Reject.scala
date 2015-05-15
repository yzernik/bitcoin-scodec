package io.github.yzernik.bitcoinscodec.messages

import io.github.yzernik.bitcoinscodec.structures.Message
import io.github.yzernik.bitcoinscodec.structures.MessageCompanion
import io.github.yzernik.bitcoinscodec.structures.VarStr

import scodec.Codec
import scodec.HListCodecEnrichedWithHListSupport
import scodec.ValueCodecEnrichedWithHListSupport
import scodec.codecs.StringEnrichedWithCodecNamingSupport
import scodec.codecs.mappedEnum
import scodec.codecs.uint8

case class Reject(
  message: String,
  ccode: Reject.CCode,
  reason: String) extends Message {
  type E = Reject
  def companion = Reject
}

object Reject extends MessageCompanion[Reject] {

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

  def codec(version: Int): Codec[Reject] = {
    ("message" | VarStr.codec) ::
      ("ccode" | ccodeCodec) ::
      ("reason" | VarStr.codec)
  }.as[Reject]

  def command = "reject"
}

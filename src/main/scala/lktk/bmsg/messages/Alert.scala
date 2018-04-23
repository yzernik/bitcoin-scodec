package lktk.bmsg.messages

import lktk.bmsg.structures._

import scodec.HListCodecEnrichedWithHListSupport
import scodec.codecs.int32
import scodec.codecs.int64
import scodec.codecs._

case class Alert(
  version: Int,
  relay_until: Long,
  expiration: Long,
  id: Int,
  cancel: Int,
  set_cancel: List[Int],
  min_ver: Int,
  max_ver: Int,
  set_sub_ver: List[String],
  priority: Int,
  comment: String,
  status_bar: String,
  reserved: String) extends Message {
  type E = Alert
  def companion = Alert
}

object Alert extends MessageCompanion[Alert] {
  def codec(version: Int) = {
    ("version" | int32) ::
      ("relay_until" | int64) ::
      ("expiration" | int64) ::
      ("id" | int32) ::
      ("cancel" | int32) ::
      ("set_cancel" | VarList.varList(int32)) ::
      ("min_ver" | int32) ::
      ("max_ver" | int32) ::
      ("set_sub_ver" | VarList.varList(VarStr.codec)) ::
      ("priority" | int32) ::
      ("comment" | VarStr.codec) ::
      ("status_bar" | VarStr.codec) ::
      ("reserved" | VarStr.codec)
  }.as[Alert]
  val command = "alert"
}

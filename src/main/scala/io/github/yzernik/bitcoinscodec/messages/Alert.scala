package io.github.yzernik.bitcoinscodec.messages

import io.github.yzernik.bitcoinscodec.structures.Message
import io.github.yzernik.bitcoinscodec.structures.MessageCompanion
import io.github.yzernik.bitcoinscodec.structures.VarList
import io.github.yzernik.bitcoinscodec.structures.VarStr

import scodec.HListCodecEnrichedWithHListSupport
import scodec.ValueCodecEnrichedWithHListSupport
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
  override def codec(version: Int) = {
    ("version" | int32) ::
      ("relay_until" | int64) ::
      ("expiration" | int64) ::
      ("id" | int32) ::
      ("cancel" | int32) ::
      ("set_cancel" | VarList(int32)) ::
      ("min_ver" | int32) ::
      ("max_ver" | int32) ::
      ("set_sub_ver" | VarList(VarStr())) ::
      ("priority" | int32) ::
      ("comment" | VarStr()) ::
      ("status_bar" | VarStr()) ::
      ("reserved" | VarStr())
  }.as[Alert]
  override val command = "alert"
}

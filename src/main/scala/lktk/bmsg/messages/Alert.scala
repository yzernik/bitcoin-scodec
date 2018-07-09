package lktk.bmsg.messages

import lktk.bmsg.structures._

import scodec.codecs._

case class Alert(
  version: Int,
  relayUntil: Long,
  expiration: Long,
  id: Int,
  cancel: Int,
  setCancel: List[Int],
  minVer: Int,
  maxVer: Int,
  setSubVer: List[String],
  priority: Int,
  comment: String,
  statusBar: String,
  reserved: String) extends Message {
  type E = Alert
  def companion = Alert
}

object Alert extends MessageCompanion[Alert] {
  def codec(version: Int) = {
    ("version" | int32L) ::
      ("relayUntil" | int64L) ::
      ("expiration" | int64L) ::
      ("id" | int32L) ::
      ("cancel" | int32L) ::
      ("setCancel" | VarList.varList(int32L)) ::
      ("minVer" | int32L) ::
      ("maxVer" | int32L) ::
      ("setSubVer" | VarList.varList(VarStr.codec)) ::
      ("priority" | int32L) ::
      ("comment" | VarStr.codec) ::
      ("statusBar" | VarStr.codec) ::
      ("reserved" | VarStr.codec)
  }.as[Alert]
  val command = "alert"
}

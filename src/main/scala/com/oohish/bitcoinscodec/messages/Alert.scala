package com.oohish.bitcoinscodec.messages

import scodec.bits.ByteVector
import scodec.Codec
import scodec.codecs
import scalaz.std.anyVal.unitInstance
import scodec.bits.BitVector
import scodec.Codec
import scodec.codecs._
import shapeless._
import com.oohish.bitcoinscodec.structures.VarList
import com.oohish.bitcoinscodec.structures.Message.Message
import com.oohish.bitcoinscodec.structures.VarStr

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
  def codec = Alert.codec
}

object Alert {
  import VarList._

  implicit val codec = {
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

}
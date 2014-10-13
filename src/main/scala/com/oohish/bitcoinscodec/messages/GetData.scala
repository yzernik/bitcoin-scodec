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
import com.oohish.bitcoinscodec.structures.Message
import com.oohish.bitcoinscodec.structures.InvVect

case class GetData(invs: List[InvVect]) extends Message {
  type E = GetData
  def codec = GetData.codec
  def command = "getdata"
}

object GetData {
  import VarList._

  implicit val codec: Codec[GetData] =
    VarList.varList(Codec[InvVect]).as[GetData]

}
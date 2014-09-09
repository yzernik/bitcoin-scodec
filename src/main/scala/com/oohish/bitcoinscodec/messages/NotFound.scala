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
import com.oohish.bitcoinscodec.structures.InvVect

case class NotFound(invs: List[InvVect]) extends Message {
  type E = NotFound
  def codec = NotFound.codec
}

object NotFound {
  import VarList._

  implicit val codec: Codec[NotFound] =
    VarList.varList(Codec[InvVect]).as[NotFound]

}
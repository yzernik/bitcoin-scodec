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

case class Inv(invs: List[InvVect]) extends Message {
  type E = Inv
  def codec = Inv.codec
}

object Inv {
  import VarList._

  implicit val codec: Codec[Inv] =
    VarList.varList(Codec[InvVect]).as[Inv]

}
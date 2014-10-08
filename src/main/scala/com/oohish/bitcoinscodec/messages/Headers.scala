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
import com.oohish.bitcoinscodec.structures.Message._

case class Headers(invs: List[Block]) extends Message {
  type E = Headers
  def codec = Headers.codec
}

object Headers {
  import VarList._
  import BlockHeader._

  implicit val codec: Codec[Headers] =
    VarList.varList(Codec[Block]).as[Headers]

}
package com.oohish.bitcoinscodec.structures

import scala.language.implicitConversions
import scalaz.{ \/, \/-, -\/, Monad, Monoid }
import scodec.Codec
import scodec.codecs._
import scodec.bits.BitVector

object VarStr {
  import VarInt._

  implicit val codec: Codec[String] = {
    val countCodec = VarInt.varIntCodec.xmap(_.value.toInt, (i: Int) => VarInt(i.toLong))
    variableSizeBytes(countCodec, ascii)
  }
}
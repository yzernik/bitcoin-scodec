package com.oohish.bitcoinscodec.structures

import scala.language.implicitConversions

import scalaz.{ \/, \/-, -\/, Monad, Monoid }

import scodec.Codec
import scodec.codecs._
import scodec.bits.BitVector

case class InvVect[A](value: List[A])

object InvVect {
  import VarInt._

  implicit def invVectCodec[A](codec: Codec[A]): Codec[InvVect[A]] = {
    val countCodec = VarInt.varIntCodec.xmap(_.value.toInt, (i: Int) => VarInt(i.toLong))
    listOfN(countCodec, codec).xmap(InvVect.apply, _.value)
  }
}
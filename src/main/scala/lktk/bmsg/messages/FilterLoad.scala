package lktk.bmsg.messages

import lktk.bmsg.structures._
import scodec.Codec
import scodec.bits.ByteVector
import scodec.codecs._

//BIP37
case class FilterLoad(
  filter: ByteVector,
  nHashFuncs: Long,
  nTweak: Long,
  nFlags: UInt8
) extends Message {
  type E = FilterLoad
  def companion = FilterLoad
}

object FilterLoad extends MessageCompanion[FilterLoad] {

  val filterCodec = {
    val countCodec = VarInt.varIntCodec.xmap(_.toInt, (i: Int) => i.toLong).>>~(bytes).flatPrepend(s => VarInt.varIntCodec :: bytes(s._1))
    countCodec
  }

  def codec(version: Int) =  (
    ("filter" | filterCodec) ::
      ("nHashFuncs" | uint32L) ::
      ("nTweak" | uint32L) ::
      ("nFlags" | UInt8.codec)
    ).as[FilterLoad]

  def command = "filterload"
}



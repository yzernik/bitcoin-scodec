package lktk.bmsg.structures

import scodec.Codec
import scodec.bits.ByteVector
import scodec.codecs._

case class ScriptWitness(
  stack: ByteVector
)

object ScriptWitness {
  val stack = {
    val countCodec = VarInt.varIntCodec.xmap(_.toInt, (i: Int) => i.toLong)
    variableSizeBytes(countCodec, bytes)
  }

  val codec: Codec[ScriptWitness] = ("stack" | stack).as[ScriptWitness]
}

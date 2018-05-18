package lktk.bmsg.structures

import scodec.Codec
import scodec.bits.ByteVector
import scodec.codecs._

case class ScriptWitness(
  stack: List[ByteVector]
)

object ScriptWitness {
  def stack = variableSizeBytesLong[ByteVector](VarInt.varIntCodec, bytes)

  def codec: Codec[ScriptWitness] = ("stack" | VarList.varList(stack)).as[ScriptWitness]
}

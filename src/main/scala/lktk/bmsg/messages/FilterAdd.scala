package lktk.bmsg.messages

import lktk.bmsg.structures.{Message, MessageCompanion}
import scodec.Attempt.{Failure}
import scodec.Codec
import scodec.bits.ByteVector
import scodec.codecs._

//BIP37
case class FilterAdd(
  byteVector: ByteVector
) extends Message {
  type E = FilterAdd
  def companion = FilterAdd
}

object FilterAdd extends MessageCompanion[FilterAdd] {
  //The data field must be smaller than or equal to 520 bytes in size
  //(the maximum size of any potentially matched object).
  val maxScriptElementSize = 520

  def codecError(v: Int)= Failure(scodec.Err(s"unable to encode version $v not supported for $command"))

  def codec(version: Int): Codec[FilterAdd] = limitedSizeBytes(maxScriptElementSize, bytes).as[FilterAdd]

  def command = "filteradd"
}
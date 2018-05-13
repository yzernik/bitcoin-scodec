package lktk.bmsg.structures

import scodec.Codec
import scodec.codecs._

case class ResultUtxo(
  version: Long,
  height: Long,
  output: TxOut
)

object ResultUtxo {
  def codec: Codec[ResultUtxo] = (
    ("version" | uint32L) ::
    ("height" | uint32L) ::
    ("output" | TxOut.codec)
  ).as[ResultUtxo]

}
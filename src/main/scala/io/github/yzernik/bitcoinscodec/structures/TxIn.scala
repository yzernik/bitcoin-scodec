package io.github.yzernik.bitcoinscodec.structures

import scodec.Codec
import scodec.codecs._

case class TxIn(
  previous_output: OutPoint,
  sig_script: Script,
  sequence: Long)

object TxIn {
  implicit val codec: Codec[TxIn] = {
    ("previous_output" | Codec[OutPoint]) ::
      ("sig_script" | Codec[Script]) ::
      ("sequence" | uint32)
  }.as[TxIn]
}

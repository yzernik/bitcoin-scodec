package com.oohish.bitcoinscodec.structures

import scodec.Codec
import scodec.codecs._
import com.oohish.bitcoinscodec.structures.OutPoint

case class TxIn(
  previous_output: OutPoint,
  sig_script: List[Int],
  sequence: Long)

object TxIn {

  implicit val codec: Codec[TxIn] = {
    ("previous_output" | Codec[OutPoint]) ::
      ("sig_script" | VarList.varList(uint8L)) ::
      ("sequence" | uint32)
  }.as[TxIn]

}
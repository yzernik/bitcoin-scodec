package io.github.yzernik.bitcoinscodec.structures

import scodec.Codec

case class TxWitness(witness: Script)

object TxWitness {
  implicit val codec: Codec[TxWitness] =
    Codec[Script].as[TxWitness]
}

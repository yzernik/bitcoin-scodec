package com.github.yzernik.bitcoinscodec.messages

import com.github.yzernik.bitcoinscodec.structures.Message
import com.github.yzernik.bitcoinscodec.structures.MessageCompanion
import com.github.yzernik.bitcoinscodec.structures.TxIn
import com.github.yzernik.bitcoinscodec.structures.TxOut
import com.github.yzernik.bitcoinscodec.structures.VarList

import scodec.Codec
import scodec.HListCodecEnrichedWithHListSupport
import scodec.ValueCodecEnrichedWithHListSupport
import scodec.codecs.StringEnrichedWithCodecNamingSupport
import scodec.codecs.uint32L

case class Tx(
  version: Long,
  tx_in: List[TxIn],
  tx_out: List[TxOut],
  lock_time: Long) extends Message {
  type E = Tx
  def companion = Tx
}

object Tx extends MessageCompanion[Tx] {
  def codec(version: Int) = {
    ("version" | uint32L) ::
      ("tx_in" | VarList.varList(Codec[TxIn])) ::
      ("tx_out" | VarList.varList(Codec[TxOut])) ::
      ("lock_time" | uint32L)
  }.as[Tx]
  def command = "tx"
}

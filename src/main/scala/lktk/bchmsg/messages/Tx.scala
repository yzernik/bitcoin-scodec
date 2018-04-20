package lktk.bchmsg.messages

import lktk.bchmsg.structures.Message
import lktk.bchmsg.structures.MessageCompanion
import lktk.bchmsg.structures.TxIn
import lktk.bchmsg.structures.TxOut
import lktk.bchmsg.structures.VarList
import lktk.bchmsg.structures._

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

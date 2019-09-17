package io.github.yzernik.bitcoinscodec.messages

import io.github.yzernik.bitcoinscodec.structures._
import io.github.yzernik.bitcoinscodec.util.Util
import scodec.Codec
import scodec.codecs._

case class Tx(
  version: Long,
  tx_in: List[TxIn],
  tx_out: List[TxOut],
  lock_time: Long) extends Message with Hashable {
  type E = Tx
  def companion = Tx

  def bytes =
    Tx.codec(0).encode(this)
      .toOption.get.toByteArray

  override def hash: Hash =
    Util.hash(bytes)

}

object Tx extends MessageCompanion[Tx] {
  override def codec(version: Int) = {
    ("version" | uint32L) ::
      ("tx_in" | VarList(Codec[TxIn])) ::
      ("tx_out" | VarList(Codec[TxOut])) ::
      ("lock_time" | uint32L)
  }.as[Tx]
  override def command = "tx"
}

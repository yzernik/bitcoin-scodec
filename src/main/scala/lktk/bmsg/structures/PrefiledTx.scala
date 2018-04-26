package lktk.bmsg.structures

//BIP152
import lktk.bmsg.messages.Tx

import scodec.Codec
import scodec.codecs._

case class PrefiledTx(
  index: Long,
  tx: Tx
)

object PrefiledTx {
  def codec(version: Int): Codec[PrefiledTx] = (
    ("index" | uint32) ::
    ("tx" | Tx.codec(version))
  ).as[PrefiledTx]
}
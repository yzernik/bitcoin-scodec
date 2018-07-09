package lktk.bmsg.structures

//BIP152
import lktk.bmsg.messages.Tx0

import scodec.Codec
import scodec.codecs._

case class PrefilledTx(
  index: Long,
  tx: Tx0
)

object PrefilledTx {
  def codec(version: Int): Codec[PrefilledTx] = (
    ("index" | uint32L) ::
    ("tx" | Tx0.codec(version))
  ).as[PrefilledTx]
}
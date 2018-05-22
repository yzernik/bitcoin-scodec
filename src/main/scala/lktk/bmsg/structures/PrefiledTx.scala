package lktk.bmsg.structures

//BIP152
import lktk.bmsg.messages.Tx0

import scodec.Codec
import scodec.codecs._

case class PrefiledTx(
  index: Long,
  tx: Tx0
)

object PrefiledTx {
  def codec(version: Int): Codec[PrefiledTx] = (
    ("index" | uint32L) ::
    ("tx" | Tx0.codec(version))
  ).as[PrefiledTx]
}
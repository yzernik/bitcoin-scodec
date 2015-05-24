package io.github.yzernik.bitcoinscodec.structures

import scodec.Codec
import scodec.codecs._

case class OutPoint(
  hash: Hash,
  index: Long)

object OutPoint {

  implicit val codec: Codec[OutPoint] = {
    ("hash" | Codec[Hash]) ::
      ("index" | uint32)
  }.as[OutPoint]

}

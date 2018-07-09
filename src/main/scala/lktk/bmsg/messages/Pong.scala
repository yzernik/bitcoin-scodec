package lktk.bmsg.messages

import lktk.bmsg.structures.UInt64.uint64L
import lktk.bmsg.structures.{Pong, TypeMsg, UInt64}
import scodec.codecs.Discriminator

object PongCodecs {
  implicit val cPongT = uint64L.xmap[Pong](Pong, _.nonce)
  implicit val discPongT: Discriminator[TypeMsg, Pong, String] = Discriminator("pong")


  def generate = Pong(UInt64.genRandom)
}
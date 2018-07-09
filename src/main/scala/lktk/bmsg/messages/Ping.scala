package lktk.bmsg.messages

import lktk.bmsg.structures.UInt64.uint64L
import lktk.bmsg.structures.{Ping, TypeMsg, UInt64}

import scodec.codecs.Discriminator

object PingCodecs {
  implicit val cPing = uint64L.xmap[Ping](Ping, _.nonce)
  implicit val discPing: Discriminator[TypeMsg, Ping, String] = Discriminator("ping")


  def generate = Ping(UInt64.genRandom)
}
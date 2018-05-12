package lktk.bmsg.messages

import lktk.bmsg.structures._

import scodec.Codec

//BIP37
case class FilterLoad(
  bloomFilter: BloomFilter
) extends Message {
  type E = FilterLoad
  def companion = FilterLoad
}

object FilterLoad extends MessageCompanion[FilterLoad] {

  def codec(version: Int): Codec[FilterLoad] = BloomFilter.codec(version).as[FilterLoad]

  def command = "filterload"
}



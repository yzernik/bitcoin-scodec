package lktk.bmsg.messages

import lktk.bmsg.structures._
import lktk.bmsg.util.ProtocolVersion
import scodec.Attempt.{Failure, Successful}
import scodec.Codec

//BIP37
case class FilterLoad(
  bloomFilter: BloomFilter
) extends Message {
  type E = FilterLoad
  def companion = FilterLoad
}

object FilterLoad extends MessageCompanion[FilterLoad] {

  def codecError(v: Int)= Failure(scodec.Err(s"unable to encode version $v not supported for $command"))

  def codec(version: Int): Codec[FilterLoad] = BloomFilter.codec(version).exmap[FilterLoad](
      f => if(version >= ProtocolVersion.v70001) Successful(FilterLoad(f)) else codecError(version),
      g => if(version >= ProtocolVersion.v70001) Successful(g.bloomFilter) else codecError(version)
    )

  def command = "filterload"
}



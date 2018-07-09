package lktk.bmsg.messages

import lktk.bmsg.structures.{Message, MessageCompanion}

import scodec.Codec
import scodec.codecs.provide

case class FilterClear() extends Message {
  type E = FilterClear
  def companion = FilterClear
}

object FilterClear extends MessageCompanion[FilterClear] {
  def codec(version: Int): Codec[FilterClear] = provide(FilterClear())
  def command = "filterclear"
}
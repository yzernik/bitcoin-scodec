package lktk.bmsg.messages

import lktk.bmsg.structures.{Message, MessageCompanion}

import scodec.Codec
import scodec.codecs.provide

//Tells the receiving peer to send the new block
//announcements using a headers message rather than an inv message
case class SendHeaders() extends Message {
  type E = SendHeaders
  def companion = SendHeaders
}

object SendHeaders extends MessageCompanion[SendHeaders] {
  def codec(version: Int): Codec[SendHeaders] = provide(SendHeaders())
  def command = "sendheaders"
}
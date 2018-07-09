package lktk.bmsg.messages

import lktk.bmsg.structures.{Message, MessageCompanion}
import lktk.bmsg.CodecSuite

import scodec.Attempt.Failure
import scodec.Codec

import scodec.codecs._
class ErrorSpec extends CodecSuite {

  case class FakeMsg(i: Int, b: String) extends Message {
    type E = FakeMsg
    def companion = FakeMsg
  }

  object FakeMsg extends MessageCompanion[FakeMsg] {
    def codec(version: Int): Codec[FakeMsg] = (uint8 :: ascii).as[FakeMsg]
    def command = "addr"
  }

  import FakeMsg._

  val expectedFailure =
    Failure(scodec.Err("0/net_addr/services: cannot acquire 64 bits from a vector that contains 8 bits"))

  val fakemsg = FakeMsg(12, "adsad")

  "A fake empty msg pretending to an Addr msg" should {
    "return Failure" in {
      val codec = Message.codec(0xDAB5BFFAL, 1)
      val decode = codec.decode(codec.encode(fakemsg).require)
      expectedFailure.toString shouldBe decode.map(_.value).toString
    }
  }
}

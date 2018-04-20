package lktk.bchmsg.messages

import lktk.bchmsg.CodecSuite

import scodec.bits.ByteVector
import scodec.bits._
import scodec.codecs._

class AlertSpec extends CodecSuite {

  import lktk.bchmsg.messages.Alert._

  //  val alert = Alert(List((1292899810L, NetworkAddress(1, Left(IPV4("10.0.0.1")), Port(8333)))))
  //  val bytes = hex"01 E2 15 10 4D 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 FF FF 0A 00 00 01 20 8D"

  "Alert codec" should {
    "roundtrip" in {
      //roundtrip(alert)
    }

  }
}

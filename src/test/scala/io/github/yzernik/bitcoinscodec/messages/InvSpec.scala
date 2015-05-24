package io.github.yzernik.bitcoinscodec.messages

import io.github.yzernik.bitcoinscodec.CodecSuite
import scodec.bits.ByteVector
import scodec.bits._
import scodec.codecs._
import io.github.yzernik.bitcoinscodec.structures._

class InvSpec extends CodecSuite {

  import Inv._

  val inv = Inv(List(InvVect(InvVect.MSG_TX,
    Hash(ByteVector.fill(32)(0x42)))))

  "Inv codec" should {
    "roundtrip" in {
      roundtrip(Inv.codec(1), inv)
      roundtrip(Message.codec(0xDAB5BFFAL, 1), inv)
    }

  }
}

package io.github.yzernik.bitcoinscodec.messages

import io.github.yzernik.bitcoinscodec.CodecSuite
import io.github.yzernik.bitcoinscodec.structures._
import scodec.bits.ByteVector

class InvSpec extends CodecSuite {

  val inv = Inv(List(InvVect(InvVect.MSG_TX,
    Hash(ByteVector.fill(32)(0x42)))))

  "Inv codec" should {
    "roundtrip" in {
      roundtrip(Inv.codec(1), inv)
      roundtrip(Message.codec(Message.TESTNET, 1), inv)
    }

  }
}

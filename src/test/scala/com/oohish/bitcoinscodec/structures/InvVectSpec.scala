package com.oohish.bitcoinscodec.structures

import com.oohish.bitcoinscodec.CodecSuite
import com.oohish.bitcoinscodec.messages.Ping
import scodec.Codec

class InvVectSpec extends CodecSuite {

  import InvVect._

  "InvVect codec" should {

    implicit val codec = invVectCodec(Codec[Ping])

    "roundtrip" in {
      roundtrip(InvVect[Ping](List()))
      roundtrip(InvVect[Ping](List(
        Ping(0),
        Ping(1),
        Ping(2))))
    }

  }
}

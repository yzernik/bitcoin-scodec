package com.oohish.bitcoinscodec.structures

import com.oohish.bitcoinscodec.CodecSuite
import com.oohish.bitcoinscodec.messages.Ping
import scodec.Codec

class VarListSpec extends CodecSuite {

  import VarList._

  "VarList codec" should {

    implicit val codec = varList(Codec[Ping])

    "roundtrip" in {
      roundtrip(codec, List())
      roundtrip(List(
        Ping(0),
        Ping(1),
        Ping(2)))
    }

  }
}

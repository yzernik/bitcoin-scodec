package io.github.yzernik.bitcoinscodec.structures

import io.github.yzernik.bitcoinscodec.CodecSuite
import io.github.yzernik.bitcoinscodec.messages.Ping
import scodec.Codec

class VarListSpec extends CodecSuite {

  import VarList._

  "VarList codec" should {

    implicit val codec = varList(Ping.codec(1))

    "roundtrip" in {
      roundtrip(codec, List())
      roundtrip(List(
        Ping(0),
        Ping(1),
        Ping(2)))
    }

  }
}

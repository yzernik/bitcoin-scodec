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
        Ping(UInt64(0)),
        Ping(UInt64(1)),
        Ping(UInt64(2))))
    }

  }
}

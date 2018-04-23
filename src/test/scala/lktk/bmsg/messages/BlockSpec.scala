package lktk.bmsg.messages

import lktk.bmsg.CodecSuite

import lktk.bmsg.messages._
import scodec.bits.ByteVector
import scodec.bits._
import scodec.codecs._

class BlockSpec extends CodecSuite {

  import lktk.bmsg.messages.Block._

  "Block codec" should {
    "roundtrip" in {
      //roundtrip(block)
    }

  }
}

package lktk.bchmsg.messages

import lktk.bchmsg.CodecSuite

import scodec.bits.ByteVector
import scodec.bits._
import scodec.codecs._

class BlockSpec extends CodecSuite {

  import lktk.bchmsg.messages.Block._

  "Block codec" should {
    "roundtrip" in {
      //roundtrip(block)
    }

  }
}

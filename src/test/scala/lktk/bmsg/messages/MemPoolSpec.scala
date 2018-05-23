package lktk.bmsg.messages

import lktk.bmsg.structures.Message
import lktk.bmsg.CodecSuite

class MemPoolSpec extends CodecSuite {

  "MemPool codec" should {
    "roundtrip" in {
      val mempool = MemPool()
      roundtrip(MemPool.codec(1), mempool)
      roundtrip(Message.codec(0xDAB5BFFAL, 1), mempool)
    }
  }
}

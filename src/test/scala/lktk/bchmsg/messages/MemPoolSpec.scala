package lktk.bchmsg.messages

import lktk.bchmsg.CodecSuite
import lktk.bchmsg.structures._
import lktk.bchmsg.messages.MemPool
import lktk.bchmsg.structures.Message

class MemPoolSpec extends CodecSuite {

  import MemPool._

  "MemPool codec" should {
    "roundtrip" in {
      val mempool = MemPool()
      roundtrip(MemPool.codec(1), mempool)
      roundtrip(Message.codec(0xDAB5BFFAL, 1), mempool)
    }
  }
}

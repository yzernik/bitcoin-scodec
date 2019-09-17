package io.github.yzernik.bitcoinscodec.messages

import io.github.yzernik.bitcoinscodec.CodecSuite
import io.github.yzernik.bitcoinscodec.structures._

class MemPoolSpec extends CodecSuite {

  "MemPool codec" should {
    "roundtrip" in {
      val mempool = MemPool()
      roundtrip(MemPool.codec(1), mempool)
      roundtrip(Message.codec(0xDAB5BFFAL, 1), mempool)
    }
  }
}

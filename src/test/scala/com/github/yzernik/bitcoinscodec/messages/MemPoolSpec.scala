package com.github.yzernik.bitcoinscodec.messages

import com.github.yzernik.bitcoinscodec.CodecSuite
import com.github.yzernik.bitcoinscodec.structures._

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

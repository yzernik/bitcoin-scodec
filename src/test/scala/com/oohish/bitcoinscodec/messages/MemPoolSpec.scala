package com.oohish.bitcoinscodec.messages

import com.oohish.bitcoinscodec.CodecSuite
import com.oohish.bitcoinscodec.structures._

class MemPoolSpec extends CodecSuite {

  import MemPool._

  "MemPool codec" should {
    "roundtrip" in {
      val mempool = MemPool()
      roundtrip(mempool)
      roundtrip(Message.codec(0xDAB5BFFAL), mempool)
    }
  }
}

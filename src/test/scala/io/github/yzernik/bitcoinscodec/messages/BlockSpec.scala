package io.github.yzernik.bitcoinscodec.messages

import io.github.yzernik.bitcoinscodec.CodecSuite
import io.github.yzernik.bitcoinscodec.structures.{Hash, Message, Network}
import scodec.bits._

class BlockSpec extends CodecSuite {

  val genesisBlockBytes =
    hex"""0100000000000000000000000000000000000000000000000000000000000
         000000000003ba3edfd7a7b12b27ac72c3e67768f617fc81bc3888a51323a
         9fb8aa4b1e5e4a29ab5f49ffff001d1dac2b7c01010000000100000000000
         00000000000000000000000000000000000000000000000000000ffffffff
         4d04ffff001d0104455468652054696d65732030332f4a616e2f323030392
         04368616e63656c6c6f72206f6e206272696e6b206f66207365636f6e6420
         6261696c6f757420666f722062616e6b73ffffffff0100f2052a010000004
         34104678afdb0fe5548271967f1a67130b7105cd6a828e03909a67962e0ea
         1f61deb649f6bc3f4cef38c4f35504e51ec112de5c384df7ba0b8d578a4c7
         02b6bf11d5fac00000000"""

  val genesisBlockHash = Hash(hex"000000000019d6689c085ae165831e934ff763ae46a2a6c172b3f1b60a8ce26f")

  "Block codec" should {
    "roundtrip" in {
      val block = Block.codec(1).decode(genesisBlockBytes.toBitVector).toOption.get.value
      block.hash shouldBe genesisBlockHash
    }

  }
}

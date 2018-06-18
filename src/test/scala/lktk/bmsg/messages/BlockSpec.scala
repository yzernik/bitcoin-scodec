package lktk.bmsg.messages

import lktk.bmsg.{CodecSuite, Generators, TestTxs}
import lktk.bmsg.TestTxs._
import lktk.bmsg.structures._
import scodec.bits._

class BlockSpec extends CodecSuite {

  val genesis = Block(
    BlockHeader(
      1,
      Hash.NULL,
      Hash(hex"4a5e1e4baab89f3a32518a88c31bc87f618f76673e2cc77ab2127b7afdeda33b"),
      1231006505L,
      486604799L,
      2083236893L
    ),
    List(TestTxs.genesisTx)
  )

  val bgenesis = hex"0100000000000000000000000000000000000000000000000000000000000000000000003BA3EDFD7A7B12B27AC72C3E67768F617FC81BC3888A51323A9FB8AA4B1E5E4A29AB5F49FFFF001D1DAC2B7C0101000000010000000000000000000000000000000000000000000000000000000000000000FFFFFFFF4D04FFFF001D0104455468652054696D65732030332F4A616E2F32303039204368616E63656C6C6F72206F6E206272696E6B206F66207365636F6E64206261696C6F757420666F722062616E6B73FFFFFFFF0100F2052A01000000434104678AFDB0FE5548271967F1A67130B7105CD6A828E03909A67962E0EA1F61DEB649F6BC3F4CEF38C4F35504E51EC112DE5C384DF7BA0B8D578A4C702B6BF11D5FAC00000000"

  "Block codec" should {
    "roundtrip genesis" in {
      roundtrip(Block.codec(1), bgenesis)
      roundtrip(Block.codec(1), genesis)
    }

    "roundtrip blocks" in {
      Generators.blockGen.map(roundtrip(Block.codec(1), _)).sample
    }

    "decode" in {
      shouldDecodeFullyTo(Block.codec(1), bgenesis.toBitVector, genesis)
    }

  }
}

package lktk.bmsg

import lktk.bmsg.messages.{Block, Headers, Tx0}
import lktk.bmsg.structures.{BlockHeader, BloomFilter, Hash}
import lktk.bmsg.structures.BloomFilter._

import org.scalacheck.Gen

import com.github.tototoshi.csv._

import scodec.bits.ByteVector


object Generators {

  def bloomfilterGen: Gen[BloomFilter] = for {
    size <- Gen.choose(1, 36000)
    filter <- Gen.containerOfN[Seq, Byte](size, Gen.choose(0, 100).map(_.toByte))
    nHashFuncs <- Gen.choose[Long](1, 50)
    tweak <- Gen.choose[Long](1, 4294967295L)
    nFlags <- Gen.oneOf(BLOOM_UPDATE_NONE, BLOOM_UPDATE_ALL, BLOOM_UPDATE_P2PUBKEY_ONLY)
  } yield BloomFilter(ByteVector(filter), nHashFuncs, tweak, nFlags)

  def txListGen(txs: List[Tx0]): Gen[List[Tx0]] = {
    val h :: tail = txs
    for {
      size <- Gen.choose(1, 3500)
      txs <- Gen.containerOfN[List, Tx0](size, Gen.oneOf(tail))
    } yield h +: txs
  }

  def blockHeaderGen: Gen[BlockHeader] = Gen.choose(1, 77).flatMap { rowNum =>
    val reader = CSVReader.open(io.Source.fromResource("blocks.csv"))
    val rowOpt = reader.toStreamWithHeaders.drop(rowNum - 1).take(1).headOption
    reader.close()

    (for {
      row <- rowOpt
      prevBlockStr <- row.get("prevBlock")
      version <- row.get("version")
      merkleRootStr <- row.get("merkleRoot")
      bits <- row.get("bits")
      prevBlock <- ByteVector.fromHex(prevBlockStr)
      merkleRoot <- ByteVector.fromHex(merkleRootStr)
    } yield BlockHeader(
      version.toLong,
      Hash(prevBlock),
      Hash(merkleRoot),
      1231006505L,
      bits.toLong,
      bits.toLong //place holder as nonce
    )).getOrElse(sys.error("fail to read file"))
  }

  def blockGen: Gen[Block] = for {
    txs <- Generators.txListGen(TestTxs.all)
    blockHeader <- Generators.blockHeaderGen
  } yield Block(blockHeader, txs)

  def headerGen: Gen[Headers] = for {
    size <- Gen.choose(1, 20)
    blocks <- Gen.containerOfN[List, Block](size, blockGen)
  } yield Headers(blocks)

}
package lktk.bmsg.structures

import scodec.Attempt.{Failure, Successful}
import scodec.Codec
import scodec.bits.ByteVector
import scodec.codecs.bytes
import scodec.codecs._

case class BloomFilter(
  filter: ByteVector,
  nHashFuncs: Long,
  nTweak: Long,
  nFlags: BloomFilter.BloomFlag
) {
  require(filter.size <= BloomFilter.maxFilterSize)
  require(nHashFuncs <= BloomFilter.maxNHashFuncs)
}

object BloomFilter {

  val maxFilterSize = 36000 //bytes
  val maxNHashFuncs = 50

  trait BloomFlag
  case object BLOOM_UPDATE_NONE extends BloomFlag
  case object BLOOM_UPDATE_ALL extends BloomFlag
  case object BLOOM_UPDATE_P2PUBKEY_ONLY extends BloomFlag
  case object BLOOM_UPDATE_MASK extends BloomFlag

  implicit val bloomFlag: Codec[BloomFlag] = mappedEnum(UInt8.codec,
    BLOOM_UPDATE_NONE -> UInt8(0),
    BLOOM_UPDATE_ALL -> UInt8(1),
    BLOOM_UPDATE_P2PUBKEY_ONLY -> UInt8(2),
    BLOOM_UPDATE_MASK -> UInt8(3)
  )

  val nHashFuncs = uint32L.narrow[Long](f =>
    if (f <= maxNHashFuncs) {
      Successful(f)
    } else {
      Failure(scodec.Err(s"value above limit $maxNHashFuncs"))
    },
      identity
  )

  val filterSize = VarInt.varIntCodec.narrow[Int](f =>
    if(f <= maxFilterSize) {
      Successful(f.toInt)
    } else {
      Failure(scodec.Err(s"value above limit $maxFilterSize"))
    },
      _.toLong
  )


  val bloomFilter = variableSizeBytes[ByteVector](filterSize, bytes)

  def codec(version: Int): Codec[BloomFilter] = (
    ("filter" | bloomFilter) ::
      ("nHashFuncs" | nHashFuncs) ::
      ("nTweak" | uint32L) ::
      ("nFlags" | bloomFlag)
    ).as[BloomFilter]

}

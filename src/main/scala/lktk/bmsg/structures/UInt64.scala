package lktk.bmsg.structures

import cats.effect.IO
import scodec.Attempt.{Failure, Successful}
import scodec.Codec
import scodec.codecs._
import spire.math.ULong
import spire.random.Dist
import spire.random.rng.SecureJava

object UInt64 {

  private val rng = IO(SecureJava().next[BigInt](Dist[ULong, BigInt](_.toBigInt)))

  private def longToBigInt(unsignedLong: Long): BigInt =
    (BigInt(unsignedLong >>> 1) << 1) + (unsignedLong & 1)

  private def bigIntToLong(n: BigInt): Long = {
    val smallestBit = (n & 1).toLong
    ((n >> 1).toLong << 1) | smallestBit
  }

  def genRandom = rng.unsafeRunSync

  implicit val uint64L: Codec[BigInt] = int64L.widen(
    longToBigInt,
    n => if(n.signum == -1) {
      Failure(scodec.Err(s"cant encode negative number in uint64L: $n"))
    } else {
      Successful(bigIntToLong(n))
    }
  )
}

package lktk.bmsg

import lktk.bmsg.messages.Tx0
import lktk.bmsg.structures.BloomFilter
import lktk.bmsg.structures.BloomFilter.{BLOOM_UPDATE_ALL, BLOOM_UPDATE_NONE, BLOOM_UPDATE_P2PUBKEY_ONLY}

import scala.collection.GenTraversable
import scala.concurrent.duration._
import shapeless.Lazy
import org.scalacheck.{Arbitrary, Gen}
import org.scalacheck.Arbitrary.arbitrary
import org.scalatest.{Matchers, WordSpec}
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import scodec._
import scodec.bits.{BitVector, ByteVector}

abstract class CodecSuite extends WordSpec with Matchers with GeneratorDrivenPropertyChecks {

  protected def roundtrip[A](a: A)(implicit c: Lazy[Codec[A]]): Unit = {
    roundtrip(c.value, a)
  }

  protected def decodeAll[A](codec: Codec[A], l: List[ByteVector]): List[A] =
    l.map(b => codec.decode(b.toBitVector).require.value)

  protected def roundtrip[A](codec: Codec[A], value: A): Unit = {
    val encoded = codec.encode(value)
    encoded shouldBe 'successful
    val Attempt.Successful(DecodeResult(decoded, remainder)) = codec.decode(encoded.require)
    remainder shouldEqual BitVector.empty
    decoded shouldEqual value
  }

  protected def roundtrip[A](codec: Codec[A], bytes: ByteVector): Unit = {
    val Attempt.Successful(DecodeResult(decoded, remainder)) = codec.decode(bytes.toBitVector)
    remainder shouldEqual BitVector.empty
    val encoded = codec.encode(decoded).require
    encoded shouldEqual bytes.toBitVector
  }

  protected def roundtripAll[A](codec: Codec[A], as: GenTraversable[A]): Unit = {
    as foreach { a => roundtrip(codec, a) }
  }

  protected def encodeError[A](codec: Codec[A], a: A, err: Err): Unit = {
    val encoded = codec.encode(a)
    encoded shouldBe Attempt.Failure(err)
  }

  protected def shouldDecodeFullyTo[A](codec: Codec[A], buf: BitVector, expected: A): Unit = {
    val Attempt.Successful(DecodeResult(actual, rest)) = codec decode buf
    rest shouldBe BitVector.empty
    actual shouldBe expected
  }

  protected def time[A](f: => A): (A, FiniteDuration) = {
    val start = System.nanoTime
    val result = f
    val elapsed = (System.nanoTime - start).nanos
    (result, elapsed)
  }

  protected def samples[A](gen: Gen[A]): Stream[Option[A]] =
    Stream.continually(gen.sample)

  protected def definedSamples[A](gen: Gen[A]): Stream[A] =
    samples(gen).flatMap { x => x }

  implicit def arbBitVector: Arbitrary[BitVector] = Arbitrary(arbitrary[Array[Byte]].map(BitVector.apply))
}
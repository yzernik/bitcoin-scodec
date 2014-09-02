package com.oohish.bitcoinscodec.utils

import scala.language.higherKinds
import scalaz.{ \/, \/-, -\/, Monad, Monoid }
import scalaz.syntax.std.option._
import scodec.bits.BitVector
import scodec.Decoder
import scodec.DecoderFunctions

/** Provides functions for working with decoders. */
trait CustomDecoderFunctions {

  /**
   * Repeatedly decodes N values of type `A` from the specified vector and returns a collection of the specified type.
   * Terminates when N values have been decoded. Exits upon the first error from decoding.
   */
  final def decodeCollectN[F[_], A](dec: Decoder[A], N: Int)(buffer: BitVector)(implicit cbf: collection.generic.CanBuildFrom[F[A], A, F[A]]): String \/ (scodec.bits.BitVector, F[A]) = {
    val bldr = cbf()
    var count = 0
    var remaining = buffer
    var error: Option[String] = None
    while (remaining.nonEmpty && count < N) {
      dec.decode(remaining) match {
        case \/-((rest, value)) =>
          bldr += value
          remaining = rest
        case -\/(err) =>
          error = Some(err)
          remaining = BitVector.empty
      }
    }
    error.toLeftDisjunction(remaining, bldr.result)
  }
}

/** Companion for [[Decoder]]. */
object CustomDecoder extends DecoderFunctions with CustomDecoderFunctions {

  /** Provides syntax for summoning a `Decoder[A]` from implicit scope. */
  def apply[A](implicit dec: Decoder[A]): Decoder[A] = dec

  /** Creates a decoder that always decodes the specified value and returns the input bit vector unmodified. */
  def point[A](a: => A): Decoder[A] = new Decoder[A] {
    private lazy val value = a
    def decode(bits: BitVector) = \/.right((bits, value))
    override def toString = s"const($value)"
  }

  implicit val monadInstance: Monad[Decoder] = new Monad[Decoder] {
    def point[A](a: => A) = Decoder.point(a)
    def bind[A, B](decoder: Decoder[A])(f: A => Decoder[B]) = decoder.flatMap(f)
  }

  implicit def monoidInstance[A: Monoid]: Monoid[Decoder[A]] = new Monoid[Decoder[A]] {
    def zero = Decoder.point(Monoid[A].zero)
    def append(x: Decoder[A], y: => Decoder[A]) = new Decoder[A] {
      private lazy val yy = y
      def decode(bits: BitVector) = for {
        first <- x.decode(bits)
        second <- yy.decode(first._1)
      } yield (second._1, Monoid[A].append(first._2, second._2))
    }
  }
}
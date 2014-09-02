package com.oohish.bitcoinscodec.utils

import scala.language.higherKinds

import scalaz.{ \/, \/-, -\/, Monad, Monoid }
import scalaz.syntax.std.option._

import scodec.bits.BitVector
import scodec.Decoder

/** Provides functions for working with decoders. */
trait DecoderFunctions {

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
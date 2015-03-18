package com.github.yzernik.bitcoinscodec.structures

import scala.BigInt

import com.github.yzernik.bitcoinscodec.structures.UInt64.bigIntCodec

import scalaz.{-\/ => -\/}
import scalaz.{\/- => \/-}
import scodec.Codec
import scodec.bits.BitVector
import scodec.codecs.uint16L
import scodec.codecs.uint32L
import scodec.codecs.uint8L

case class VarInt(value: Long)

object VarInt {

  import scodec.codecs._

  implicit val varIntCodec = Codec[Long](
    (n: Long) =>
    n match {
      case i if (i < 0xfd) =>
        uint8L.encode(i.toInt)
      case i if (i < 0xffff) =>
        for {
          a <- uint8L.encode(0xfd)
          b <- uint16L.encode(i.toInt)
        } yield a ++ b
      case i if (i < 0xffffffffL) =>
        for {
          a <- uint8L.encode(0xfe)
          b <- uint32L.encode(i)
        } yield a ++ b
      case i =>
        for {
          a <- uint8L.encode(0xff)
          b <- Codec[BigInt].encode(BigInt(i))
        } yield a ++ b
    },
    (buf: BitVector) => {
      uint8L.decode(buf) match {
        case \/-((rest, byte)) =>
          byte match {
            case 0xff =>
              Codec[BigInt].decode(rest)
                .map { case (a, b) => (a, b.toLong) }
            case 0xfe =>
              uint32L.decode(rest)
                .map { case (a, b) => (a, b) }
            case 0xfd =>
              uint16L.decode(rest)
                .map { case (a, b) => (a, b.toLong) }
            case _ =>
              \/-((rest, byte.toLong))
          }
        case -\/(err) =>
          -\/(err)
      }
    })

}

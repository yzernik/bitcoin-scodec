package com.oohish.bitcoinscodec.structures

import scalaz.{ \/, \/-, -\/, Monad, Monoid }

import scodec.Codec
import scodec.bits.BitVector

case class VarInt(value: Long)

object VarInt {

  import scodec.codecs._

  implicit val varIntCodec = Codec[VarInt](
    (varint: VarInt) =>
      varint match {
        case VarInt(i) if (i < 0xfd) =>
          uint8.encode(i.toInt)
        case VarInt(i) if (i < 0xffff) =>
          for {
            a <- uint8.encode(0xfd)
            b <- uint16.encode(i.toInt)
          } yield a ++ b
        case VarInt(i) if (i < 0xffffffff) =>
          for {
            a <- uint8.encode(0xfe)
            b <- uint32.encode(i)
          } yield a ++ b
        case VarInt(i) =>
          for {
            a <- uint8.encode(0xff)
            b <- Codec[UInt64].encode(UInt64(UInt64.bigIntToLong(BigInt(i))))
          } yield a ++ b
      },
    (buf: BitVector) => {
      uint8.decode(buf) match {
        case \/-((rest, byte)) =>
          byte match {
            case 0xff =>
              Codec[UInt64].decode(rest)
                .map { case (a, b) => (a, VarInt(b.value)) }
            case 0xfe =>
              uint32.decode(rest)
                .map { case (a, b) => (a, VarInt(b)) }
            case 0xfd =>
              uint16.decode(rest)
                .map { case (a, b) => (a, VarInt(b)) }
            case _ =>
              \/-((rest, VarInt(byte)))
          }
        case -\/(err) =>
          -\/(err)
      }
    })

}
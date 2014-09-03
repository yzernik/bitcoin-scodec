package com.oohish.bitcoinscodec.structures

import scodec.Codec
import scodec.bits.BitVector

object VarInt {

  import scodec.codecs._

  val varIntCodec = Codec[Long](
    (n: Long) => n match {
      case i: Long if (i < 0xfd) =>
        uint8.encode(i.toByte)
      case i: Long if (i < 0xffff) =>
        for {
          a <- uint8.encode(0xfd.toByte)
          b <- uint16.encode(i.toByte)
        } yield a ++ b
      case i: Long if (i < 0xffffffff) =>
        for {
          a <- uint8.encode(0xfe.toByte)
          b <- uint32.encode(i.toByte)
        } yield a ++ b
      case i: Long =>
        for {
          a <- uint8.encode(0xff.toByte)
          b <- Codec[UInt64].encode(UInt64(UInt64.bigIntToLong(BigInt(i))))
        } yield a ++ b
    },
    (buf: BitVector) => {
      val y = uint32.decode(buf)

      y
    })

}
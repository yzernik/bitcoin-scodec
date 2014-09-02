package com.oohish.bitcoinscodec.structures

import scodec.Codec
import scodec.bits.BitVector

object VarInt {

  import scodec.codecs._

  val varIntCodec = Codec[Long](
    (n: Long) =>

      n match {
        case i: Long if (i < 0xfd) => uint8.encode(n.toByte)
        case i: Long if (i < 0xffff) => uint16.encode(n.toByte)
        case i: Long if (i < 0xffffffff) => uint32.encode(n.toByte)
        case i: Long => uint32.encode(n.toByte)
      },
    (buf: BitVector) => {
      val y = uint32.decode(buf)
      y
    })

}
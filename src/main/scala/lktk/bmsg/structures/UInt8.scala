package lktk.bmsg.structures

import scodec.Codec
import scodec.codecs.uint8L

case class UInt8(value: Int)

object UInt8 {

  implicit val codec: Codec[UInt8] = uint8L.xmap(UInt8.apply, _.value)

}

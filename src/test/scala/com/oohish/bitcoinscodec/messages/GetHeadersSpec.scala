package com.oohish.bitcoinscodec.messages

import com.oohish.bitcoinscodec.CodecSuite
import scodec.bits.ByteVector
import scodec.bits._
import scodec.codecs._
import scalaz.\/
import com.oohish.bitcoinscodec.structures.InvVect
import com.oohish.bitcoinscodec.structures.Hash
import com.oohish.bitcoinscodec.structures.VarInt

class GetHeadersSpec extends CodecSuite {

  import GetHeaders._

  val getheaders = GetHeaders(
    1L,
    0L,
    List(),
    Hash(ByteVector.fill(32)(0x42)))

  val getheaders2 = GetHeaders(
    1L,
    1L,
    List(Hash(ByteVector.fill(32)(0x42))),
    Hash(ByteVector.fill(32)(0x42)))

  "GetHeaders codec" should {
    "roundtrip" in {
      roundtrip(getheaders)
      roundtrip(getheaders2)
    }

  }
}

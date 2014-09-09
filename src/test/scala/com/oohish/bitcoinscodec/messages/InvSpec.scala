package com.oohish.bitcoinscodec.messages

import com.oohish.bitcoinscodec.CodecSuite
import scodec.bits.ByteVector
import scodec.bits._
import scodec.codecs._
import scalaz.\/
import com.oohish.bitcoinscodec.structures.InvVect
import com.oohish.bitcoinscodec.structures.Hash

class InvSpec extends CodecSuite {

  import Inv._

  val inv = Inv(List(InvVect(InvVect.MSG_TX,
    Hash(ByteVector.fill(32)(0x42)))))

  "Inv codec" should {
    "roundtrip" in {
      roundtrip(inv)
    }

  }
}

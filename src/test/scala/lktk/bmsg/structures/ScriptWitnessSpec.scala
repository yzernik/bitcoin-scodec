package lktk.bmsg.structures

import lktk.bmsg.CodecSuite

import scodec.bits._

class ScriptWitnessSpec extends CodecSuite {

  val (size1, witness) = (hex"48",hex"3045022100f9d3fe35f5ec8ceb07d3db95adcedac446f3b19a8f3174e7e8f904b1594d5b43022074d995d89a278bd874d45d0aea835d3936140397392698b7b5bbcdef8d08f2fd01")

  val (size2, witness2) = (hex"23", hex"21038262a6c6cec93c2d3ecd6c6072efea86d02ff8e3328bbd0242b20af3425990acac")

  "Scriptwitness codec" should {

    "roundtrip" in {
      roundtrip(ScriptWitness.codec, ScriptWitness(witness))
    }

    "decode" in {
      shouldDecodeFullyTo(ScriptWitness.codec, (size2 ++ witness2).toBitVector, ScriptWitness(witness2))
    }
  }
}

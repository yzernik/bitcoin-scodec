package lktk.bmsg.messages

import lktk.bmsg.structures._
import scodec.Attempt.{Failure, Successful}
import scodec.Codec
import scodec.bits.ByteVector
import scodec.codecs._

//There are two version of Tx. one regular tx and another with witness data
//the decoding and encoding policy is to try the regular tx first, if it fails it will try the wtx.
sealed trait Tx0 extends Message {
  type E = Tx0
  def companion = Tx0
}

case class Tx(
  version: Long,
  txIn: List[TxIn],
  txOut: List[TxOut],
  lockTime: Long
) extends Tx0

case class Wtx(
  version: Long,
  txIn: List[TxIn],
  txOut: List[TxOut],
  txWitness: List[ScriptWitness],
  lockTime: Long
) extends Tx0

object Tx0 extends MessageCompanion[Tx0] {

  val witnessFlag = for {
    marker <- uint8L.encode(0)
    flag <- uint8L.encode(1)
  } yield (marker ++ flag).toByteVector

  val flag: Codec[ByteVector] = bytes(2).narrow[ByteVector](f =>
    if(f == witnessFlag.require) Successful(f) else Failure(scodec.Err(s"Witness flag not set correctly $f")),
    identity
  )

  def codec(version: Int): Codec[Tx0] = (wtx(version) :+: tx(version)).as[Tx0].choice

  def tx(version: Int) = (
    ("version" | uint32L) ::
    ("txIn" | VarList.varList(Codec[TxIn])) ::
    ("txOut" | VarList.varList(Codec[TxOut])) ::
    ("lockTime" | uint32L)
  ).as[Tx]

  def wtx(version: Int) = (
    ("version" | uint32L) ::
    flag.unit(witnessFlag.require) ::
    ("txIn" | VarList.varList(Codec[TxIn])) ::
    ("txOut" | VarList.varList(Codec[TxOut])) ::
    ("txWitnesses" | VarList.varList(ScriptWitness.codec)) ::
    ("lockTime" | uint32L)
  ).as[Wtx]

  def command = "tx"
}

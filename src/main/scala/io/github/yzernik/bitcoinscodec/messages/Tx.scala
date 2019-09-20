package io.github.yzernik.bitcoinscodec.messages

import io.github.yzernik.bitcoinscodec.structures._
import io.github.yzernik.bitcoinscodec.util.Util
import scodec.Codec
import scodec.bits.BitVector
import scodec.codecs._

case class Tx(
  version: Long,
  flag: Boolean,
  tx_in: List[TxIn],
  tx_out: List[TxOut],
  tx_witness: List[TxWitness],
  lock_time: Long) extends Message with Hashable {
  require(flag || tx_witness.isEmpty)
  require(lock_time >= 0)

  type E = Tx
  override def companion = Tx

  def bytes =
    Tx.codec(0).encode(this)
      .toOption.get.toByteVector

  override def hash =
    Util.hash(bytes)

  def isLocked: Boolean =
    lock_time != 0L
}

object Tx extends MessageCompanion[Tx] {

  private def decodeFlag(bits: BitVector) = {
    for {
      v <- uint32L.decode(bits)
      witnessFlag <- Codec[WitnessFlag].decode(v.remainder)
    } yield witnessFlag
  }

  private def decodeWithFlag(bits: BitVector, flag: Boolean, version: Int) = {
    val txCodec: Codec[Tx] =
      if (flag) codecWithWitness(version)
      else codecWithoutWitness(version)
    txCodec.decode(bits)
  }

  override def codec(version: Int) = {

    def encode(tx: Tx) = {
      val txCodec: Codec[Tx] =
        if (tx.flag) codecWithWitness(version)
        else codecWithoutWitness(version)
      txCodec.encode(tx)
    }

    def decode(bits: BitVector) =
      for {
        f <- decodeFlag(bits)
        flag = f.value.isWitness
        tx <- decodeWithFlag(bits, flag, version)
      } yield tx

    Codec[Tx](encode _, decode _)
  }.as[Tx]

  def codecWithoutWitness(version: Int) = {
    ("version" | uint32L) ::
      ("flag" | provide(false)) ::
      ("tx_in" | VarList(Codec[TxIn])) ::
      ("tx_out" | VarList(Codec[TxOut])) ::
      ("tx_witness" | provide(List[TxWitness]())) ::
      ("lock_time" | uint32L)
    }.as[Tx]

  def codecWithWitness(version: Int) = {
    ("version" | uint32L) ::
      ("flag" | booleanFlagCodec) ::
      ("tx_in" | VarList(Codec[TxIn])) ::
      ("tx_out" | VarList(Codec[TxOut])) ::
      ("tx_witness" | VarList(Codec[TxWitness])) ::
      ("lock_time" | uint32L)
    }.as[Tx]

  case class WitnessFlag(flag1: Int, flag2: Int) {
    def isWitness: Boolean =
      flag1 == 0 && flag2 == 1
  }

  implicit def flagCodec: Codec[WitnessFlag] = {
    ("flag1" | uint8L) ::
      ("flag2" | uint8L)
    }.as[WitnessFlag]

  def booleanFlagCodec: Codec[Boolean] = {
    ("flag" | mappedEnum(flagCodec,
      false -> WitnessFlag(0, 0), true -> WitnessFlag(0, 1)))
    }.as[Boolean]

  override def command = "tx"
}

package lktk.bmsg.structures

import cats.Show
import cats.syntax.show._

import lktk.bmsg.util.Util

import scodec.Attempt.{Failure, Successful}
import scodec.bits.BitVector
import scodec.Codec
import scodec.codecs._
import lktk.bmsg.messages.PingCodecs._
import lktk.bmsg.messages.PongCodecs._

sealed trait TypeMsg

case class Ping(nonce: BigInt) extends TypeMsg
case class Pong(nonce: BigInt) extends TypeMsg

object TypeMessage {
  val commandCodec: Codec[String] = fixedSizeBytes(8, ascii)

  implicit val discTypeMsg: Discriminated[TypeMsg, String] = Discriminated(commandCodec)


  implicit val sMsg: Show[TypeMsg] = {
    case _: Ping => "ping"
    case _: Pong => "pong"
  }

  def payloadCodec(cmd: String): Codec[TypeMsg] = Codec.coproduct[TypeMsg].discriminatedBy(provide(cmd.trim)).auto

  def decodeHeader(bits: BitVector, magic: Long, version: Int) =
    for {
      m <- uint32L.decode(bits).flatMap { mg =>
        if (mg.value == magic) Successful(mg) else Failure(scodec.Err("magic did not match."))
      }
      command <- commandCodec.decode(m.remainder)
      length <- uint32L.decode(command.remainder)
      chksum <- uint32L.decode(length.remainder)
      (payload, _) = chksum.remainder.splitAt(length.value * 8)
    } yield (command.value.trim, length, chksum.value, payload)

  def decodePayload(payload: BitVector, version: Int, chksum: Long, command: String) =
    payloadCodec(command).narrow[TypeMsg](p =>
      if(Util.checksum(payload.toByteVector) == chksum) {
        Successful(p)
      } else {
        Failure(scodec.Err("checksum did not match."))
      },
      identity
    ).decode(payload)

  def codec(magic: Long, version: Int): Codec[TypeMsg] = {
    def encode(msg: TypeMsg) = for {
      magic <- uint32L.encode(magic)
      cmd = msg.show
      command <- commandCodec.encode(cmd)
      payload <- payloadCodec(cmd).encode(msg)
      length <- uint32L.encode(payload.length / 8)
      chksum <- uint32L.encode(Util.checksum(payload.toByteVector))
    } yield magic ++ command ++ length ++ chksum ++ payload

    def decode(bits: BitVector) =
      for {
        metadata <- decodeHeader(bits, magic, version)
        (command, length, chksum, payload) = metadata
        msg <- decodePayload(payload, version, chksum, command)
      } yield msg

    Codec[TypeMsg](encode _, decode _)
  }
}
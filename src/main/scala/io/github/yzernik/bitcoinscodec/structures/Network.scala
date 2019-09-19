package io.github.yzernik.bitcoinscodec.structures

object Network {

  abstract class NetworkParams(val magic: Long)

  case object MainnetParams extends NetworkParams(0xD9B4BEF9L)

  case object TestnetParams extends NetworkParams(0xDAB5BFFAL)

  case object Testnet3Params extends NetworkParams(0x0709110BL)

  case object NamecoinParams extends NetworkParams(0xFEB4BEF9L)

}
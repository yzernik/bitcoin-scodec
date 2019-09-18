package io.github.yzernik.bitcoinscodec.structures

object Network {

  abstract class NetworkConfig(val magic: Long)

  case object Mainnet extends NetworkConfig(0xD9B4BEF9L)

  case object Testnet extends NetworkConfig(0xDAB5BFFAL)

  case object Testnet3 extends NetworkConfig(0x0709110BL)

  case object Namecoin extends NetworkConfig(0xFEB4BEF9L)

}
package lktk.bmsg.structures


object ServiceFlags {

  // Nothing
  final val NodeNone = BigInt(0)

  // NodeNetwork means that the node is capable of serving the block chain.
  // is unset by SPV clients or other peers that just want network services but don't provide them.
  final val NodeNetwork = BigInt(1 << 0)

  // NodeGetUTXO means the node is capable of responding to the getutxo
  // protocol request.
  final val NodeGetUTXO = BigInt(1 << 1)

  // NodeBloom means the node is capable and willing to handle bloom-filtered
  // connections.
  final val NodeBloom = BigInt(1 << 2)

  // NodeWitness indicates that a node can be asked for blocks and transactions including
  // witness data.
  final val NodeWitness = BigInt(1 << 3)

  // NodeXThin means the node supports Xtreme Thinblocks.
  final val NodeXThin = BigInt(1 << 4)

  // Bits 24-31 are reserved for temporary experiments. Just pick a bit that
  // isn't getting used, or one not being used much, and notify the
  // bitcoin-development mailing list. Remember that service bits are just
  // unauthenticated advertisements, so your code must be robust against
  // collisions and other cases where nodes may be advertising a service they
  // do not actually support. Other service bits should be allocated via the
  // BIP process.

  val serviceFlag = UInt64.uint64L
}
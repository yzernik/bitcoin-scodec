package lktk.bmsg.structures

//Added in BIP152
case class HeaderAndShortIDs(
                            header: BlockHeader,
                            nonce: BigInt,
                            shortIds: List[BigInt],
                            prefilledTxn: List[PrefiledTx]
                            )

# bmsg


[![GitHub license](https://img.shields.io/badge/license-MIT-blue.svg)](https://raw.githubusercontent.com/floreslorca/bmsg/master/LICENSE) [![Build Status](https://travis-ci.org/floreslorca/bmsg.svg?branch=master)](https://travis-ci.org/floreslorca/bmsg) [![Coverage Status](https://coveralls.io/repos/github/floreslorca/bmsg/badge.svg?branch=master)](https://coveralls.io/github/floreslorca/bmsg?branch=master)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/f824534cf0a1418ab862e2287cbfb777)](https://www.codacy.com/app/floreslorca/bmsg?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=floreslorca/bmsg&amp;utm_campaign=Badge_Grade)

Library for encoding Bitcoin messages. Particularly for Bitcoin Core and Bitcoin Cash implementations
Resources used for spec:

* [`Bitcoin developer reference`](https://bitcoin.org/en/developer-reference#p2p-network)
* [`Bitcoin wiki`](https://en.bitcoin.it/wiki/Protocol_documentation)

This library implements all the p2p messages used by the following clients:
* [`Bitcoin ABC`](https://www.bitcoinabc.org/)
* [`Bitcoin Core`](https://bitcoin.org/en/bitcoin-core/)
* [`Bitcoin Unlimited`](https://www.bitcoinunlimited.info/)
* [`Bitcoin XT`](https://bitcoinxt.software/)

It includes specific messages implemented on some of them:
* [`BIP144`](https://github.com/bitcoin/bips/blob/master/bip-0144.mediawiki) Segwit
* [`BUIP10`](https://github.com/BitcoinUnlimited/BitcoinUnlimited/blob/release/doc/bu-xthin-protocol.md) Xthinblocks
* [`BIP64`](https://github.com/bitcoin/bips/blob/master/bip-0064.mediawiki) getutxo message

The library is written in Scala following Functional Programming principles and using [scodec](https://github.com/scodec/scodec).

### How to use

Add the following to your build.sbt:

```
libraryDependencies += "lktk" %% "bmsg" % "0.4.0"
```

with the following resolver

```
resolvers += Resolver.bintrayRepo("floreslorca", "io")
```

### Encode a Bitcoin message

create a message codec

```
> import lktk.bmsg.structures.Message

scala> val codec = Message.codec(0xD9B4BEF9L, 60002) // on the main network, using version 60002.
```

encode a ping message
```
scala> import lktk.bmsg.messages._

scala> codec.encode(Ping(BigInt(1234)))
res0: scodec.Attempt[scodec.bits.BitVector] = Successful(BitVector(256 bits, 0xf9beb4d970696e67000000000000000008000000433ba813d204000000000000))
```

decode a pong message
```
scala> import scodec.bits._

scala> codec.decode(hex"f9beb4d9706f6e67000000000000000040000000433ba813d204000000000000".toBitVector)
res1: scodec.Attempt[scodec.DecodeResult[scala> import lktk.bmsg.structures.Message]] = Successful(DecodeResult(Pong(1234),BitVector(empty)))
```

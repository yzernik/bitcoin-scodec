# bmsg


[![GitHub license](https://img.shields.io/badge/license-MIT-blue.svg)](https://raw.githubusercontent.com/lktkorg/bmsg/master/LICENSE) [![Build Status](https://travis-ci.org/lktkorg/bmsg.svg?branch=master)](https://travis-ci.org/lktkorg/bmsg) [![Coverage Status](https://coveralls.io/repos/github/lktkorg/bmsg/badge.svg?branch=master)](https://coveralls.io/github/lktkorg/bmsg?branch=master)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/f824534cf0a1418ab862e2287cbfb777)](https://www.codacy.com/app/lktkorg/bmsg?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=lktkorg/bmsg&amp;utm_campaign=Badge_Grade)

Library for encoding Bitcoin messages. Particularly for Bitcoin Core and Bitcoin Cash implementations

The bitcoin protocol works by sending messages between other nodes in the network. This is the structure of every message:

| Field Size | Description | Serial Data type | Comments |
|:---:       | :---:       | :---:  | ---      |
| 4          | magic       | uint32L  | Magic value indicating message origin network, and used to seek to next message when stream state is unknown |
| 12         | command     | ascii  | ASCII string identifying the packet content, NULL padded (non-NULL padding results in packet rejected) |
| 4          | length      | uint32L | Length of payload in number of bytes |
| 4          | checksum    | uint32L | First 4 bytes of sha256(sha256(payload)) |
| ?          | payload     | byteVector | The actual data |

The sufix "L" refers to little endian. Almost all integers are encoded in [little endian](https://en.wikipedia.org/wiki/Endianness) except for Port number and IP. All encoding and decoding of message headers is handled by this library. In addition, concrete implementations of most of the currently supported bitcoin messages are provided. For these supported messages, all of the details of marshalling and unmarshalling to and from the wire using bitcoin encoding are handled so the caller doesn't have to concern themselves with the specifics.

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
libraryDependencies += "lktk" %% "bmsg" % "0.4.5"
```

with the following resolver

```
resolvers += Resolver.bintrayRepo("lktk", "io")
```

### Encode and Decode a Bitcoin message

create a message codec. This is your interface for encoding and decoding all bitcoin messages

```
> import lktk.bmsg.structures.Message

scala> val codec = Message.codec(0xD9B4BEF9L, 70012) // on the main network, using version 70012.
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

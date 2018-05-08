# bmsg


[![GitHub license](https://img.shields.io/badge/license-MIT-blue.svg)](https://raw.githubusercontent.com/floreslorca/bmsg/master/LICENSE) [![Build Status](https://travis-ci.org/floreslorca/bmsg.svg?branch=master)](https://travis-ci.org/floreslorca/bmsg) [![Coverage Status](https://coveralls.io/repos/github/floreslorca/bmsg/badge.svg?branch=master)](https://coveralls.io/github/floreslorca/bmsg?branch=master)


Library for encoding Bitcoin messages. Particularly for Bitcoin Core and Bitcoin Cash implementations. [network protocol](https://bitcoin.org/en/developer-reference#p2p-network) in Scala using [scodec](https://github.com/scodec/scodec).

Supports BIP152, BIP37

### How to use

Add the following to your build.sbt:

```
libraryDependencies += "lktk" %% "bmsg" % "0.3.1"
```

with the following resolver

```
resolvers += Resolver.bintrayRepo("floreslorca", "io")
```

### Encode a Bitcoin message

create a message codec

```
scala> import lktk.bmsg.structures.Message

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

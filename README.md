# bchmsg

[![Build Status](https://travis-ci.org/floreslorca/bchmsg.svg?branch=master)](https://travis-ci.org/floreslorca/bchmsg) [![Coverage Status](https://img.shields.io/coveralls/floreslorca/bchmsg.svg)](https://coveralls.io/r/floreslorca/bchmsg?branch=master)


Library for encoding Bitcoin Cash [network protocol](https://en.bitcoin.it/wiki/Protocol_Specification) in Scala using [scodec](https://github.com/scodec/scodec).


### How to use

Add the following to your build.sbt:


with the following resolver


### Encode a Bitcoin message

create a message codec

```
scala> import lktk.bchmsg.structures.Message

scala> val codec = Message.codec(0xD9B4BEF9L, 60002) // on the main network, using version 60002.
```

encode a ping message
```
scala> import lktk.bchmsg.messages._

scala> codec.encode(Ping(BigInt(1234)))
res0: scodec.Attempt[scodec.bits.BitVector] = Successful(BitVector(256 bits, 0xf9beb4d970696e67000000000000000008000000433ba813d204000000000000))
```

decode a pong message
```
scala> import scodec.bits._

scala> codec.decode(hex"f9beb4d9706f6e67000000000000000040000000433ba813d204000000000000".toBitVector)
res1: scodec.Attempt[scodec.DecodeResult[scala> import lktk.bchmsg.structures.Message]] = Successful(DecodeResult(Pong(1234),BitVector(empty)))
```

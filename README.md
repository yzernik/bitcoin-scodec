# bitcoin-scodec 

[![Build Status](https://travis-ci.org/yzernik/bitcoin-scodec.svg?branch=master)](https://travis-ci.org/yzernik/bitcoin-scodec) [![Coverage Status](https://img.shields.io/coveralls/yzernik/bitcoin-scodec.svg)](https://coveralls.io/r/yzernik/bitcoin-scodec?branch=master)


Library for encoding Bitcoin [network protocol](https://en.bitcoin.it/wiki/Protocol_Specification) in Scala using [scodec](https://github.com/scodec/scodec).


### How to use

Add the following to your build.sbt:


```
libraryDependencies += "io.github.yzernik" %% "bitcoin-scodec" % "0.2.7"
```

with the following resolver


``` scala
resolvers += "yzernik repo" at "http://dl.bintray.com/yzernik/maven/"
```

### Encode a Bitcoin message

create a message codec

```
scala> import io.github.yzernik.bitcoinscodec.structures.Message

scala> val codec = Message.codec(0xD9B4BEF9L, 60002) // on the main network, using version 60002.
```

encode a ping message
```
scala> import io.github.yzernik.bitcoinscodec.messages._

scala> codec.encode(Ping.generate)
res1: scodec.Attempt[scodec.bits.BitVector] = Successful(BitVector(256 bits, 0xf9beb4d970696e670000000000000000080000005ab304eee3c5449c496dbb7d))
```

decode a pong message
```
scala> import scodec.bits._

scala> codec.decode(hex"0xf9beb4d9706f6e67000000000000000008000000fb8558ea62e96de205767e7c".toBitVector)
res3: scodec.Attempt[scodec.DecodeResult[io.github.yzernik.bitcoinscodec.structures.Message]] = Successful(DecodeResult(Pong(7c7e7605e26de962),BitVector(empty)))
```

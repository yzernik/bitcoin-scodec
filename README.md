# bitcoin-scodec 

[![Build Status](https://travis-ci.org/yzernik/bitcoin-scodec.svg?branch=master)](https://travis-ci.org/yzernik/bitcoin-scodec) [![Coverage Status](https://img.shields.io/coveralls/yzernik/bitcoin-scodec.svg)](https://coveralls.io/r/yzernik/bitcoin-scodec?branch=master)


Library for encoding Bitcoin [network protocol](https://en.bitcoin.it/wiki/Protocol_Specification) in Scala using [scodec](https://github.com/scodec/scodec).


### How to use

Add the following to your build.sbt:


```
libraryDependencies += "io.github.yzernik" %% "bitcoin-scodec" % "0.3.0"
```

with the following resolver


``` scala
resolvers += "yzernik repo" at "http://dl.bintray.com/yzernik/maven/"
```

### Encode a Bitcoin message

create a message codec

```
scala> import io.github.yzernik.bitcoinscodec.structures.{Message, Network}

scala> val codec = Message.codec(Network.Mainnet, 60002) // on the main network, using version 60002.
```

encode a ping message
```
scala> import io.github.yzernik.bitcoinscodec.messages._

scala> val ping = Ping.generate
ping: io.github.yzernik.bitcoinscodec.messages.Ping = Ping(UInt64(0xd60c4f7719060e2e))

scala> codec.encode(ping)
res7: scodec.Attempt[scodec.bits.BitVector] = Successful(BitVector(256 bits, 0xf9beb4d970696e670000000000000000080000003c0a64b42e0e0619774f0cd6))
```

decode a pong message
```
scala> import scodec.bits._

scala> val bytes = hex"0xf9beb4d9706f6e670000000000000000080000003c0a64b42e0e0619774f0cd6"
bytes: scodec.bits.ByteVector = ByteVector(32 bytes, 0xf9beb4d9706f6e670000000000000000080000003c0a64b42e0e0619774f0cd6)

scala> codec.decode(bytes.toBitVector)
res1: scodec.Attempt[scodec.DecodeResult[io.github.yzernik.bitcoinscodec.structures.Message]] = Successful(DecodeResult(Pong(UInt64(0xd60c4f7719060e2e)),BitVector(empty)))
```

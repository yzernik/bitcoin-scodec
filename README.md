bitcoin-scodec 
========
[![Build Status](https://travis-ci.org/yzernik/bitcoin-scodec.svg?branch=master)](https://travis-ci.org/yzernik/bitcoin-scodec) [![Coverage Status](https://img.shields.io/coveralls/yzernik/bitcoin-scodec.svg)](https://coveralls.io/r/yzernik/bitcoin-scodec?branch=master)


Library for encoding Bitcoin [network protocol](https://en.bitcoin.it/wiki/Protocol_Specification) in Scala using [scodec](https://github.com/scodec/scodec).


### How to use ###

Add the following to your build.sbt:


```
libraryDependencies += "com.oohish" %% "bitcoin-scodec" % "0.1.10"
```

with the following resolver


``` scala
resolvers += "yzernik repo" at "http://dl.bintray.com/yzernik/maven/"
```

### Encode a Bitcoin message ###

create a message codec

```
import com.oohish.bitcoinscodec.structures.Message

val codec = Message.codec(0xD9B4BEF9L) // on the main network
```

encode a ping message
```
import com.oohish.bitcoinscodec.messages._

codec.encode(Ping(BigInt(1234)))
// scalaz.\/[String,scodec.bits.BitVector] = \/-(BitVector(256 bits, 0xf9beb4d970696e67000000000000000040000000433ba813d204000000000000))
```

decode a pong message
```
import scodec.bits._

codec.decode(hex"f9beb4d9706f6e67000000000000000040000000433ba813d204000000000000".toBitVector)
// scalaz.\/[String,(scodec.bits.BitVector, com.oohish.bitcoinscodec.structures.Message.Message)] = \/-((BitVector(empty),Pong(1234)))
```

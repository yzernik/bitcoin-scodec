bitcoin-scodec 
========
[![Build Status](https://travis-ci.org/yzernik/bitcoin-scodec.svg?branch=master)](https://travis-ci.org/yzernik/bitcoin-scodec) [![Coverage Status](https://img.shields.io/coveralls/yzernik/bitcoin-scodec.svg)](https://coveralls.io/r/yzernik/bitcoin-scodec?branch=master)


Library for encoding Bitcoin [network protocol](https://en.bitcoin.it/wiki/Protocol_Specification) in Scala using [scodec](https://github.com/scodec/scodec).


### How to use ###

Add the following to your build.sbt:


```
libraryDependencies += "com.oohish" %% "bitcoin-scodec" % "0.1.0"
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

encode a verack message
```
import com.oohish.bitcoinscodec.messages._

codec.encode(Verack())
// scalaz.\/[String,scodec.bits.BitVector] = \/-(BitVector(192 bits, 0xf9beb4d976657261636b000000000000000000005df6e0e2))
```

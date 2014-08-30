name := "bitcoinz"

version := "0.1.0"

scalaVersion := "2.11.2"

resolvers += Resolver.sonatypeRepo("releases")

libraryDependencies ++= Seq(
    "org.typelevel" %% "scodec-core" % "1.2.0"
)
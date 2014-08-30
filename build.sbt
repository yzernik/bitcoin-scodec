name := "bitcoinz"

version := "0.1.0"

scalaVersion := "2.11.2"

resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/"

libraryDependencies ++= Seq(
    "org.typelevel" %% "scodec-core" % "1.2.0"
)
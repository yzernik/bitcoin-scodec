import scoverage.ScoverageSbtPlugin.instrumentSettings
import org.scoverage.coveralls.CoverallsPlugin.coverallsSettings

name := "bitcoin-scodec"

version := "0.1.0"

scalaVersion := "2.11.2"

resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/"

libraryDependencies ++= Seq(
    "org.typelevel" %% "scodec-core" % "1.3.0",
    "org.scalatest" %% "scalatest" % "2.2.0" % "test",
    "org.scalacheck" %% "scalacheck" % "1.11.3" % "test",
    "org.bouncycastle" % "bcpkix-jdk15on" % "1.50" % "test"
)

scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature")

instrumentSettings

coverallsSettings
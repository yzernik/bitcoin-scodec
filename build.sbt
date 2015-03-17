name := "bitcoin-scodec"

organization := "com.github.yzernik"

version := "0.2.5"

scalaVersion := "2.11.4"

licenses += ("MIT", url("http://opensource.org/licenses/MIT"))

resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/"

libraryDependencies ++= Seq(
  "org.typelevel"                 %% "scodec-core"    % "1.3.0",
  "org.scalatest"                 %% "scalatest"      % "2.2.0"  % "test",
  "org.scalacheck"                %% "scalacheck"     % "1.11.3" % "test",
  "org.bouncycastle"               % "bcpkix-jdk15on" % "1.50"   % "test",
  "com.github.scala-incubator.io" %% "scala-io-core"  % "0.4.3"  % "test"
)

scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature")

scoverage.ScoverageSbtPlugin.instrumentSettings

org.scoverage.coveralls.CoverallsPlugin.coverallsSettings

seq(bintrayPublishSettings:_*)

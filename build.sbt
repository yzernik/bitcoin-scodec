name := "bchmsg"

organization := "lktk"

version := "0.3"

scalaVersion := "2.12.4"

scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature", "-Ypartial-unification")

licenses += ("MIT", url("http://opensource.org/licenses/MIT"))

resolvers += "Sonatype Public" at "https://oss.sonatype.org/content/groups/public/"

resolvers += "Scalaz Bintray Repo" at "http://dl.bintray.com/scalaz/releases"

libraryDependencies ++= Seq(
  "org.scodec"                    %% "scodec-core"    % "1.10.3",
  "org.scalatest"                 %% "scalatest"      % "3.0.5"  % "test",
  "org.scalacheck"                %% "scalacheck"     % "1.13.4" % "test"
//  "com.github.scala-incubator.io" %% "scala-io-core"  % "0.4.3"  % "test"
)

//scoverage.ScoverageSbtPlugin.instrumentSettings
//org.scoverage.coveralls.CoverallsPlugin.coverallsSettings

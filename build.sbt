name := "bitcoin-scodec"

organization := "io.github.yzernik"

version := "0.2.9"

scalaVersion := "2.12.10"

licenses += ("MIT", url("http://opensource.org/licenses/MIT"))

resolvers += "Sonatype Public" at "https://oss.sonatype.org/content/groups/public/"

resolvers += "Scalaz Bintray Repo" at "http://dl.bintray.com/scalaz/releases"

libraryDependencies ++= Seq(
  "org.scodec"                    %% "scodec-bits"    % "1.1.6",
  "org.scodec"                    %% "scodec-core"    % "1.10.3",
  "org.scalatest"                 %% "scalatest"      % "3.0.8"  % "test",
  "org.scalacheck"                %% "scalacheck"     % "1.14.0" % "test"
)

libraryDependencies ++= {
  if (scalaBinaryVersion.value startsWith "2.10")
    Seq(compilerPlugin("org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full))
  else Nil
}

scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature")

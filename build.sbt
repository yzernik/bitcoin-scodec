name := "bmsg"

organization := "lktk"

version := "0.4.4"

scalaVersion := "2.12.6"

scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature", "-Ypartial-unification")

licenses += ("MIT", url("http://opensource.org/licenses/MIT"))

coverageEnabled := true

scmInfo := Some(ScmInfo(url("https://github.com/floreslorca/bmsg"), "git@github.com:floreslorca/bmsg.git"))

bintrayPackageLabels := Seq("bitcoin")

bintrayRepository := "io"

skip in publish := true

libraryDependencies ++= Seq(
  "org.scodec"           %% "scodec-core" % "1.10.3",
  "org.typelevel"        %% "spire"       % "0.14.1",
  "org.scalatest"        %% "scalatest"   % "3.0.5"   % "test",
  "org.scalacheck"       %% "scalacheck"  % "1.13.4"  % "test",
  "com.github.tototoshi" %% "scala-csv"   % "1.3.5"   % "test"
)


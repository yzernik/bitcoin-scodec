name := "bmsg"

organization := "lktkorg"

version := "0.4.5"

scalaVersion := "2.12.6"

scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature", "-Ypartial-unification")

licenses += ("MIT", url("http://opensource.org/licenses/MIT"))

coverageEnabled := true //has to be turned off when publishing

scmInfo := Some(ScmInfo(url("https://github.com/lktkorg/bmsg"), "git@github.com:lktkorg/bmsg.git"))

bintrayPackageLabels := Seq("bitcoin")

bintrayRepository := "io"

libraryDependencies ++= Seq(
  "org.scodec"           %% "scodec-core" % "1.10.3",
  "org.typelevel"        %% "spire"       % "0.14.1",
  "org.scalatest"        %% "scalatest"   % "3.0.5"   % "test",
  "org.scalacheck"       %% "scalacheck"  % "1.13.4"  % "test",
  "com.github.tototoshi" %% "scala-csv"   % "1.3.5"   % "test"
)


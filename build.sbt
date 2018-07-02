name := "bmsg"

organization := "org.lktk"

scalaVersion := "2.12.6"
scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature", "-Ypartial-unification")

coverageEnabled := true //has to be turned off when publishing

homepage := Some(url("https://www.lktk.org"))
scmInfo := Some(ScmInfo(url("https://github.com/lktkorg/bmsg"), "git@github.com:lktkorg/bmsg.git"))
licenses += ("MIT", url("http://opensource.org/licenses/MIT"))

bintrayPackageLabels := Seq("bitcoin", "bitcoin cash", "p2p", "blockchain")
bintrayOrganization := Some("lktk")
bintrayRepository := "io"

libraryDependencies ++= Seq(
  "org.scodec"           %% "scodec-core" % "1.10.3",
  "org.typelevel"        %% "spire"       % "0.14.1",
  "org.typelevel"        %% "cats-effect" % "1.0.0-RC2",
  "org.scalatest"        %% "scalatest"   % "3.0.5"   % "test",
  "org.scalacheck"       %% "scalacheck"  % "1.13.4"  % "test",
  "com.github.tototoshi" %% "scala-csv"   % "1.3.5"   % "test"
)


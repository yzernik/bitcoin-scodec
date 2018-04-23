name := "bmsg"

organization := "lktk"

version := "0.3"

scalaVersion := "2.12.4"

scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature", "-Ypartial-unification")

licenses += ("MIT", url("http://opensource.org/licenses/MIT"))

resolvers += Resolver.bintrayRepo("floreslorca", "maven")

bintrayPackageLabels := Seq("bitcoin")

libraryDependencies ++= Seq(
  "org.scodec"                    %% "scodec-core"    % "1.10.3",
  "org.scalatest"                 %% "scalatest"      % "3.0.5"  % "test",
  "org.scalacheck"                %% "scalacheck"     % "1.13.4" % "test"
)


name := "bmsg"

organization := "lktk"

version := "0.3"

scalaVersion := "2.12.4"

scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature", "-Ypartial-unification")

licenses += ("MIT", url("http://opensource.org/licenses/MIT"))

coverageEnabled := true

bintrayPackageLabels := Seq("bitcoin")

bintrayRepository := "io"

skip in publish := true

libraryDependencies ++= Seq(
  "org.scodec"                    %% "scodec-core"    % "1.10.3",
  "org.scalatest"                 %% "scalatest"      % "3.0.5"  % "test",
  "org.scalacheck"                %% "scalacheck"     % "1.13.4" % "test"
)


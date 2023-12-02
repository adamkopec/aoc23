ThisBuild / scalaVersion := "2.13.12"
ThisBuild / organization := "name.kopec.adam"

lazy val commonSettings = Seq(
  libraryDependencies ++= Seq(
    "org.scalactic" %% "scalactic" % "3.2.17",
    "org.scalatest" %% "scalatest" % "3.2.17" % "test"
  )
)

lazy val d1 = (project in file("d1"))
  .settings(commonSettings)

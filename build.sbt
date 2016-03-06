name := """chatwork4s"""

version := "0.1-SNAPSHOT"

scalaVersion := "2.11.7"

libraryDependencies += "org.specs2" %% "specs2-core" % "3.0.1" % "test"

libraryDependencies += "org.specs2" %% "specs2-scalacheck" % "3.0.1" % "test"

libraryDependencies += "org.scalacheck" % "scalacheck_2.11" % "1.12.2"

libraryDependencies ++=  {

  val scalaJHttpVersion = "2.2.1"
  val playJsonVersion = "2.3.10"
  val playJsonNamingVersion = "0.2.0"

  Seq(
    "org.scalaj" %% "scalaj-http" % scalaJHttpVersion,
    "com.typesafe.play" %% "play-json" % playJsonVersion,
    "com.github.tototoshi" %% "play-json-naming" % playJsonNamingVersion
  )
}
name := """chatwork4s"""

version := "0.2.5-SNAPSHOT"

scalaVersion := "2.11.8"

scalacOptions ++= Seq("-feature")

libraryDependencies += "org.specs2" %% "specs2-core" % "3.0.1" % "test"

libraryDependencies += "org.specs2" %% "specs2-scalacheck" % "3.0.1" % "test"

libraryDependencies += "org.scalacheck" % "scalacheck_2.11" % "1.12.2"

libraryDependencies ++=  {

  val scalaJHttpVersion = "2.2.1"
  val playJsonVersion = "2.5.6"
  val playJsonNamingVersion = "1.1.0"

  Seq(
    "org.scalaj" %% "scalaj-http" % scalaJHttpVersion,
    "com.typesafe.play" %% "play-json" % playJsonVersion,
    "com.github.tototoshi" %% "play-json-naming" % playJsonNamingVersion
  )
}

organization := "tv.kazu"

organizationName := ""

organizationHomepage := Some(new URL("http://kazu.tv"))

publishMavenStyle := true

publishTo := {
  val nexus = "https://oss.sonatype.org/"
  if (version.value.trim.endsWith("SNAPSHOT"))
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases"  at nexus + "service/local/staging/deploy/maven2")
}

pomExtra := (
  <scm>
    <url>https://github.com/k4200/chatwork4s</url>
    <connection>scm:git:git@github.com:k4200/chatwork4s.git</connection>
    <developerConnection>scm:git:https://github.com/k4200/chatwork4s.git</developerConnection>
  </scm>
  <developers>
    <developer>
      <id>k4200</id>
      <name>KASHIMA Kazuo</name>
      <email>k4200 [at] kazu.tv</email>
      <url>https://twitter.com/k4200</url>
    </developer>
  </developers>
)
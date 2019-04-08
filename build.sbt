
ThisBuild / scalaVersion     := "2.12.8"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.everforth"
ThisBuild / organizationName := "Everforth Co.,Ltd."
ThisBuild / licenses         := Seq("The Apache Software License, Version 2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0.txt"))

lazy val root = (project in file("."))
    .settings(
      name := "chatwork4s",
      scalacOptions ++= Seq("-feature"),
      libraryDependencies ++= Seq(
        "org.scalatest" %% "scalatest" % "3.0.5" % Test,
        "org.scalaj" %% "scalaj-http" % "2.4.1",
        "com.typesafe.play" %% "play-json" % "2.7.2",
        "com.github.tototoshi" %% "play-json-naming" % "1.3.0"
      )
    )

/*
resolvers += "Scalaz Bintray Repo" at "http://dl.bintray.com/scalaz/releases"

libraryDependencies += "org.specs2" %% "specs2-core" % "3.0.1" % "test"

libraryDependencies += "org.specs2" %% "specs2-scalacheck" % "3.0.1" % "test"

libraryDependencies += "org.scalacheck" % "scalacheck_2.11" % "1.12.2"

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
*/
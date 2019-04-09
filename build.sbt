
ThisBuild / scalaVersion     := "2.12.8"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / isSnapshot       := true
ThisBuild / organization     := "com.everforth"
ThisBuild / organizationName := "Everforth Co.,Ltd."
ThisBuild / licenses         := Seq("The Apache Software License, Version 2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0.txt"))

lazy val root = (project in file("."))
    .enablePlugins(AutoMkcolPlugin)
    .settings(
      name := "chatwork4s",
      scalacOptions ++= Seq("-feature"),
      libraryDependencies ++= Seq(
        "org.scalatest" %% "scalatest" % "3.0.5" % Test,
        "org.scalaj" %% "scalaj-http" % "2.4.1",
        "com.typesafe.play" %% "play-json" % "2.7.2",
        "com.github.tototoshi" %% "play-json-naming" % "1.3.0"
      ),
      credentials += Credentials(Path.userHome / ".sbt" / ".credentials"),
      publishTo := {
        val backlog = "https://everforth.backlog.jp/dav/EFSDK/maven/"
        if (isSnapshot.value)
          Some("Backlog snapshots" at backlog + "snapshots")
        else
          Some("Backlog releases" at backlog + "releases")
      }
    )

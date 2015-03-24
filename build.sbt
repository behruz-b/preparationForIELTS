name := "preparationForIELTS"

version := "1.0"

lazy val `preparationforielts` = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  "org.postgresql" % "postgresql" % "9.3-1100-jdbc4",
  "com.typesafe.slick" %% "slick" % "2.1.0" withJavadoc() withSources(),
  "com.typesafe.play" %% "play-slick" % "0.8.0" withJavadoc() withSources()
)
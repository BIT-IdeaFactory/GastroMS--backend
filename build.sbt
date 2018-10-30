name         := "play-slick-postgresql-Gastro"
version      := "1.0"
scalaVersion := "2.11.5"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache,
  ws
)

libraryDependencies ++= Seq(
  "com.typesafe.slick" %% "slick"      % "2.1.0",
  // "com.typesafe.slick" %% "slick" % "3.2.3",
  "org.postgresql"     %  "postgresql" % "9.3-1102-jdbc41"
)

javacOptions ++= Seq("-source", "1.6", "-target", "1.6")

organization := "edu.holycross.shot"
name := "scs"
version := "0.1.0"


scalaVersion := "2.11.6"
lazy val finchVersion = "0.12.0"



resolvers  :=  Seq(
  "Sonatype Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"
)

libraryDependencies := Seq(
  "com.github.finagle" %% "finch-core" % finchVersion,
  "com.github.finagle" %% "finch-circe" % finchVersion,
  "com.google.code.findbugs" % "jsr305" % "3.0.1",

  "edu.holycross.shot" %% "cite" % "3.1.0",
  "edu.holycross.shot" %% "ohco2" % "2.1.0"
)


val circeVersion = "0.7.0"
libraryDependencies ++= Seq(
  "io.circe" %% "circe-core",
  "io.circe" %% "circe-generic",
  "io.circe" %% "circe-parser"
).map(_ % circeVersion)

lazy val runScs = taskKey[Unit]("Run Scala CITE Services in Finch")
addCommandAlias("scs", "; runScs")
fullRunTask(runScs, Compile, "edu.holycross.shot.scs.CiteServices")

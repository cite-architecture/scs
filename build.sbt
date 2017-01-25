import sbt._

organization := "edu.holycross.shot"
name := "scs"
version := "0.1.0"
scalaVersion := "2.11.6"


lazy val finchVersion = "0.7.0"

resolvers  :=  Seq(
  "Akka Snapshot Repository" at "http://repo.akka.io/snapshots/",
  "Sonatype Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"
)

libraryDependencies := Seq(
  "com.typesafe.scala-logging" %% "scala-logging" % "3.1.0",
  "ch.qos.logback" % "logback-classic" % "1.1.2",
  "com.github.finagle" %% "finch-core" % finchVersion,
  "com.github.finagle" %% "finch-argonaut" % finchVersion
)


lazy val runScs = taskKey[Unit]("Run Scala CITE Services in Finch")
addCommandAlias("scs", "; runScs")
fullRunTask(runScs, Compile, "edu.holycross.shot.scs.CiteServices")

import sbt._

organization := "edu.holycross.shot"
name := "scs"
version := "0.1.0"
scalaVersion := "2.11.6"


lazy val finchVersion = "0.7.0"
//lazy val finchVersion = "0.12.0"

resolvers  :=  Seq(
  "Sonatype Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"
)

libraryDependencies := Seq(
  "com.github.finagle" %% "finch-core" % finchVersion
)


lazy val runScs = taskKey[Unit]("Run Scala CITE Services in Finch")
addCommandAlias("scs", "; runScs")
fullRunTask(runScs, Compile, "edu.holycross.shot.scs.CiteServices")

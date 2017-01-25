
organization := "edu.holycross.shot"
name := "scs"
version := "0.1.0"


scalaVersion := "2.11.6"



resolvers  :=  Seq(
  "Sonatype Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"
)

lazy val finchVersion = "0.8.0"
libraryDependencies := Seq(
  "com.github.finagle" %% "finch-core" % finchVersion
)





//lazy val finchVersion = "0.9.0"

lazy val runScs = taskKey[Unit]("Run Scala CITE Services in Finch")
addCommandAlias("scs", "; runScs")
fullRunTask(runScs, Compile, "edu.holycross.shot.scs.CiteServices")

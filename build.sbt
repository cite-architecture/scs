
organization := "edu.holycross.shot"
name := "scs"
version := "0.2.0"


scalaVersion := "2.12.3"
lazy val finchVersion = "0.16.0-M1"



resolvers  :=  Seq(
  "Sonatype Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots",
  "uh-nexus" at "http://beta.hpcc.uh.edu/nexus/content/groups/public"
)

resolvers += Resolver.jcenterRepo
resolvers += Resolver.bintrayRepo("neelsmith", "maven")

libraryDependencies := Seq(
  "com.github.finagle" %% "finch-core" % finchVersion,
  "org.scalatest" %% "scalatest" % "3.0.1" % "test",
  "com.github.finagle" %% "finch-circe" % finchVersion,
  "com.google.code.findbugs" % "jsr305" % "2.0.1",
  "edu.holycross.shot.cite" %% "xcite" % "2.7.1",
  "edu.holycross.shot" %% "cex" % "6.1.0",
  "edu.holycross.shot" %% "ohco2" % "10.1.2",
  "edu.holycross.shot" %% "scm" % "5.1.3"
)


val circeVersion = "0.8.0"
libraryDependencies ++= Seq(
  "io.circe" %% "circe-core",
  "io.circe" %% "circe-generic",
  "io.circe" %% "circe-parser"
).map(_ % circeVersion)

lazy val runScs = taskKey[Unit]("Run Scala CITE Services in Finch")
addCommandAlias("scs", "; runScs")
fullRunTask(runScs, Compile, "edu.holycross.shot.scs.CiteServices")

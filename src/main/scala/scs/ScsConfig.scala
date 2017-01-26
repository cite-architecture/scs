package edu.holycross.shot.scs
import java.io.File
import scala.io.Source

case class ScsConfig(configMap: Map[String,String])

object ScsConfig {

  def apply(configFileName: String ): ScsConfig = {
    val confLines = Source.fromFile(configFileName).getLines.toVector
    val confEntries = confLines.filterNot(_.head == '#').map(s => s.split("=")).map( ar => (ar(0), ar(1)) ).toMap

    ScsConfig(confEntries)
  }
}

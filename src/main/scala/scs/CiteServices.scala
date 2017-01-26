package edu.holycross.shot.scs

import io.finch._
import com.twitter.finagle.Http
import com.twitter.util.Await


import edu.holycross.shot.ohco2._
import edu.holycross.shot.cite._

// plain text reply to request on localhost:8080/scs
object CiteServices extends App {

  import com.twitter.io.{Reader, Buf}
  val settings = ScsConfig("src/main/resources/config.properties")



  val corpus = Corpus( settings.configMap("textdata"))

  val msg = "CITE  services will live here, and local data sources configured for cts data from  " + settings.configMap("textdata")


  val api: Endpoint[String] = get("texts" :: string ) { u: String => {
    val urn = CtsUrn(u)
    Ok(msg + " for urn " + u)
  }}

  Await.ready(Http.server.serve(":8080", api.toServiceAs[Text.Plain]))

}

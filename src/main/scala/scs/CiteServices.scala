package edu.holycross.shot.scs

import io.finch._
import io.finch.circe._
import io.circe.generic.auto._

import com.twitter.finagle.{Http, Service}
import com.twitter.finagle.http.{Request, Response}
import com.twitter.util.Await


import edu.holycross.shot.ohco2._
import edu.holycross.shot.cite._

// plain text reply to request on localhost:8080/scs
object CiteServices extends App {

  import com.twitter.io.{Reader, Buf}
  val settings = ScsConfig("src/main/resources/config.properties")



  val corpus = Corpus( settings.configMap("textdata"))

  val msg = "CITE  services will live here, and local data sources configured for cts data from  " + settings.configMap("textdata")


  val text: Endpoint[Vector[CitableNode]] = get("texts" :: string ) {
    u: String => {
      val urn = CtsUrn(u)
      val urnList = corpus.urnMatch(urn)
      Ok(urnList)
    }
  }

  val works: Endpoint[Vector[CtsUrn]] = get("texts" ) {
    Ok(corpus.citedWorks)
  }

  val reff: Endpoint[Vector[CtsUrn]] = get("texts" :: "reff" :: string ) { u : String  => {
      val urn = CtsUrn(u)
      val urnList = corpus.getValidReff(urn)
      Ok(urnList)
    }
  }

  val firstNode: Endpoint[CitableNode] = get("texts" :: "first" :: string) {
    u : String => {
      val urn = CtsUrn(u)
      val urnList = corpus.urnMatch(urn)
      Ok(urnList(0))
    }
  }

  val svc : Service[Request, Response] = (text :+: works :+: firstNode :+: reff).toServiceAs[Application.Json]
  Await.ready(Http.server.serve(":8080", svc))

}

package edu.holycross.shot.scs

import scala.io.Source
import io.finch._
import io.finch.circe._
import io.circe.generic.auto._
import io.circe.{Encoder, Json}
//import io.finch.response.{ EncodeJsonResponse, EncodeResponse }
import io.finch.Encode
import com.twitter.finagle.{Http, Service}
import com.twitter.finagle.http.{Request, Response}
import com.twitter.util.Await


import edu.holycross.shot.ohco2._
import edu.holycross.shot.cite._
import edu.holycross.shot.scm._


/*
case class ScsException(s: String) extends Exception {
  override def getMessage: String = "Cite Service exception: problem with " + s
}
*/

object CiteServices extends App {

  // load an OHCO2 corpus:
  val settings = ScsConfig("src/main/resources/config.properties")
  val cexFilePath = settings.configMap("cexdata")

  val cexData = Source.fromFile(cexFilePath).getLines.mkString("\n")
  val library = CiteLibrary(cexData,"#",",")
  val textRepo = library.textRepository.get
  val corpus = textRepo.corpus
  val textCatalog = textRepo.catalog

  //val corpus = Corpus( settings.configMap("textdata"))
  // load a catalog:
  //val textCatalog = edu.holycross.shot.ohco2.Catalog(settings.configMap("textcatalog"))


    // CatalogEntry's in text Catalog
    val ohco2catalog: Endpoint[Vector[CatalogEntry]] = get("textcatalog") {
      Ok(textCatalog.texts)
    }

    val ohco2work: Endpoint[Vector[CatalogEntry]] = get("textcatalog" :: string) {
      u: String => {
        val urn = CtsUrn(u)
        Ok(textCatalog.entriesForUrn(urn))
      }
    }


  // CitableNodes in corpus
  val works: Endpoint[Vector[CtsUrn]] = get("texts" ) {
    Ok(corpus.citedWorks)
  }


  val text: Endpoint[Corpus] = get("texts" :: string ) {
    u: String => {
      val urn = CtsUrn(u)
      //val urnList = corpus.urnMatch(urn)
      val urnList = corpus ~~ urn
      Ok(urnList)
    }
  }

  val reff: Endpoint[Vector[CtsUrn]] = get("texts" :: "reff" :: string ) { u : String  => {
      val urn = CtsUrn(u)
      val urnList = (corpus ~~ urn).urns
      Ok(urnList)
    }
  }

  val firstNode: Endpoint[CitableNode] = get("texts" :: "first" :: string) {
    u : String => {
      val urn = CtsUrn(u)
      val newCorp = corpus ~~ (urn)
      if ( !newCorp.isEmpty) {
        Ok(newCorp.firstNode(urn))
      } else {
        throw ScsException("No urns found matching " + u)
      }
    }
  }


  val svc : Service[Request, Response] = (text :+: works :+: firstNode :+: reff :+: ohco2catalog :+: ohco2work).handle({
      case se: ScsException => NotFound(se)
      case ce: CiteException => NotFound(ce)

    }).
    toServiceAs[Application.Json]

  Await.ready(Http.server.serve(":8080", svc))

}

package edu.holycross.shot.scs

import io.finch._
import com.twitter.finagle.Http
import com.twitter.util.Await
import java.io.File

// plain text reply to request on localhost:8080/scs
object CiteServices extends App {

  import com.twitter.io.{Reader, Buf}
  val config = new java.io.File(".").getCanonicalPath
  val ctsCatalog = new java.io.File(config,"catalog.txt")




  val msg = "CITE  services will live here, and use CTS cataloged in " + ctsCatalog.toString()
  val api: Endpoint[String] = get("scs") { Ok(msg) }

  Await.ready(Http.server.serve(":8080", api.toServiceAs[Text.Plain]))

}

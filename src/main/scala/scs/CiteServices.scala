package edu.holycross.shot.scs

import io.finch.route._
import com.twitter.finagle.Httpx
import com.twitter.util.Await
import java.io.File

object CiteServices extends App {

  import com.twitter.io.{Reader, Buf}
  val config = new java.io.File(".").getCanonicalPath
  val ctsCatalog = new java.io.File(config,"catalog.txt")
  val msg = "CITE  services will live here, and use CTS cataloged in " + ctsCatalog.toString()

  Await.ready(Httpx.serve(":8080", (Get / "scs" /> msg).toService))

}

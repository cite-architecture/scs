package edu.holycross.shot.scs

import io.finch.route._
import com.twitter.finagle.Httpx

object CiteServices extends App {
  Httpx.serve(":8080", (Get / "scs" /> "CITE  services will live here").toService)

  println("Press <enter> to exit.")
  Console.in.read.toChar
}

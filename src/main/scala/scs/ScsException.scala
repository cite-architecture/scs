package edu.holycross.shot.scs

case class ScsException(s: String) extends Exception {
  override def getMessage: String = s
}

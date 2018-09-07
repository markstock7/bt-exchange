package org.bt.exchange.base

trait AsJava[From, To] {
	def convert(from: From): To
}

object AsJava {
	def apply[From, To](from: From)(implicit asJava: AsJava[From, To]): To =
		asJava.convert(from)

	def apply[From, To](fromOpt: Option[From])(implicit asJava: AsJava[From, To]): To =
		fromOpt match {
			case Some(from) => apply(from)
			case None       => null.asInstanceOf[To]
		}

	def or[From, To](fromOpt: Option[From], or: To)(implicit asJava: AsJava[From, To]): To =
		fromOpt match {
			case Some(from) => apply(from)
			case None => or
		}

	implicit val x0: AsJava[String, String]          = t => t
	implicit val x1: AsJava[Long, java.lang.Long]    = t => t
	implicit val x2: AsJava[Int, java.lang.Integer]  = t => t
	implicit val x5: AsJava[BigDecimal, String]      = _.toString
	implicit def instanceOpt[E <: Enumeration#Value]: AsJava[E, String] = e => e.toString
}
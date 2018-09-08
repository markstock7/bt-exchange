package org.bt.exchange.bitfinex.domain

object Side extends Enumeration {
	type Side = Value
	val LONG = Value("long")
	val SHORT = Value("short")
}

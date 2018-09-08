package org.bt.exchange.bitfinex.domain


object Section extends Enumeration {
	type Section = Value
	val LAST = Value("last")
	val HIST = Value("hist")
}

package org.bt.exchange.bitfinex.domain

object Key extends Enumeration {
	type Key = Value
	val FUNDING_SIZE = Value("funding.size")
	val CREDITS_SIZE = Value("credits.size")
	val CREDIT_SIZE_SYM = Value("credits.size.sym")
	val POS_SIZE = Value("pos.size")
}


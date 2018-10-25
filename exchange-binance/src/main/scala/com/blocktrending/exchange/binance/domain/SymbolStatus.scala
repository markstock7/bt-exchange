package com.blocktrending.exchange.binance.domain

object SymbolStatus extends Enumeration {
	type SymbolStatus = Value

	val PRE_TRADING = Value("PRE_TRADING")
	val TRADING = Value("TRADING")
	val POST_TRADING = Value("POST_TRADING")
	val END_OF_DAY = Value("END_OF_DAY")
	val HALT = Value("HALT")
	val AUCTION_MATCH = Value("AUCTION_MATCH")
	val BREAK = Value("BREAK")
}

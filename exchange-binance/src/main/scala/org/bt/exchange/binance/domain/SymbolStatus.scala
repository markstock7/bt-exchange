package org.bt.exchange.binance.domain


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

object SymbolType extends Enumeration {
	type SymbolType = Value

	val STOP = Value("STOP")
}

object OrderType extends Enumeration {
	type OrderType = Value

	val LIMIT = Value("LIMIT")
	val MARKET = Value("MARKET")
	val STOP_LOSS = Value("STOP_LOSS")
	val STOP_LOSS_LIMIT = Value("STOP_LOSS_LIMIT")
	val TAKE_PROFIT = Value("TAKE_PROFIT")
	val TAKE_PROFIT_LIMIT = Value("TAKE_PROFIT_LIMIT")
	val LIMIT_MAKER = Value("LIMIT_MAKER")
}

object TimeInForce extends Enumeration {
	type TimeInForce = Value

	val GTC = Value("GTC")
	val IOC = Value("IOC")
	val FOK = Value("FOK")
}

object RateLimitInterval extends Enumeration {
	type RateLimitInterval = Value
	val SECOND = Value("SECOND")
	val MINUTE = Value("MINUTE")
	val DAY = Value("DAY")
}
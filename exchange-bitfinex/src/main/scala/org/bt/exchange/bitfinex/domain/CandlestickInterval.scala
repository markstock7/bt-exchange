package org.bt.exchange.bitfinex.domain

object CandlestickInterval extends Enumeration {
	type CandlestickInterval = Value

	val ONE_MINUTE = Value("1m")
	val FIVE_MINUTES = Value("5m")
	val FIFTEEN_MINUTES = Value("15m")
	val HALF_HOURLY = Value("30m")
	val HOURLY = Value("1h")
	val THREE_HOURLY = Value("3h")
	val SIX_HOURLY = Value("6h")
	val TWELEV_HOURLY = Value("12h")
	val DAILY = Value("1D")
	val WEEKLY = Value("7D")
	val FOURTEEN_DAYS = Value("14D")
	val MONTHLY = Value("1M")
}

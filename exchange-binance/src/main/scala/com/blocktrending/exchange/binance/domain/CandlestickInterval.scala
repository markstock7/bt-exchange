package com.blocktrending.exchange.binance.domain

object CandlestickInterval extends Enumeration {
	type CandlestickInterval = Value

	val ONE_MINUTE = Value("1m")
	val THREE_MINUTES = Value("3m")
	val FIVE_MINUTES = Value("5m")
	val FIFTEEN_MINUTES = Value("15m")
	val HALF_HOURLY = Value("30m")
	val HOURLY = Value("1h")
	val TWO_HOURLY = Value("2h")
	val FOUR_HOURLY = Value("4h")
	val SIX_HOURLY = Value("6h")
	val EIGHT_HOURLY = Value("8h")
	val TWELEVE_HOURLY = Value("12h")
	val DAILY = Value("1d")
	val THREE_DAY = Value("3d")
	val WEEKLY = Value("1w")
	val MONTHLY = Value("1M")

	def interval2Period(interval: CandlestickInterval): Long = {
		interval match {
			case ONE_MINUTE => 60 * 1000
			case THREE_MINUTES => 3 * 60 * 1000
			case FIVE_MINUTES => 5 * 60 * 1000
			case FIFTEEN_MINUTES => 15 * 60 * 1000
			case HALF_HOURLY => 30 * 60 * 1000
			case HOURLY => 60 * 60 * 1000
			case TWO_HOURLY => 2 * 60 * 60 * 1000
			case FOUR_HOURLY => 4 * 60 * 60 * 1000
			case SIX_HOURLY => 6 * 60 * 60 * 1000
			case EIGHT_HOURLY => 8 * 60 * 60 * 1000
			case TWELEVE_HOURLY => 12 * 60 * 60 * 1000
			case DAILY => 24 * 60 * 60 * 1000
			case THREE_DAY => 3 * 24 * 60 * 60 * 1000
			case WEEKLY => 7 * 24 * 60 * 60 * 1000
			case MONTHLY => 30 * 24 * 60 * 60 * 1000
		}
	}
}

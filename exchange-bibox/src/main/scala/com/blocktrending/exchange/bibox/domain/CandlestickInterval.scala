package com.blocktrending.exchange.bibox.domain

object CandlestickInterval extends Enumeration {
	type CandlestickInterval = Value

	val ONE_MINUTE = Value("1min")
	val THREE_MINUTES = Value("3min")
	val FIVE_MINUTES = Value("5min")
	val FIFTEEN_MINUTES = Value("15min")
	val HALF_HOURLY = Value("30min")
	val HOURLY = Value("1hour")
	val TWO_HOURLY = Value("2hour")
	val FOUR_HOURLY = Value("4hour")
	val SIX_HOURLY = Value("6hour")
	val TWELEVE_HOURLY = Value("12hour")
	val DAILY = Value("day")
	val WEEKLY = Value("week")

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
			case TWELEVE_HOURLY => 12 * 60 * 60 * 1000
			case DAILY => 24 * 60 * 60 * 1000
			case WEEKLY => 7 * 24 * 60 * 60 * 1000
		}
	}
}

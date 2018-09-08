package org.bt.exchange.huobipro.domain

object CandlestickInterval extends Enumeration {
	type CandlestickInterval = Value

	val ONE_MINUTE = Value("1min")
	val FIVE_MINUTES = Value("5min")
	val FIFTEEN_MINUTES = Value("15min")
	val HALF_HOURLY = Value("30min")
	val HOURLY = Value("60min")
	val DAILY = Value("1day")
	val WEEKLY = Value("1week")
	val MONTHLY = Value("1mon")
	val YEAR = Value("1year")

}

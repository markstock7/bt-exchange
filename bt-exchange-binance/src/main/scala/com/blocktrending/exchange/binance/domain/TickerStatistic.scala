package com.blocktrending.exchange.binance.domain

case class TickerStatistic(
	symbol: String,
	priceChange: BigDecimal,
	priceChangePercent: BigDecimal,
	prevClosePrice: BigDecimal,
	openPrice: BigDecimal,
	highPrice: BigDecimal,
	lowPrice: BigDecimal,
	volume: BigDecimal,
	quoteVolume: BigDecimal,
	openTime: Long,
	closeTime: Long,
	count: Int
)

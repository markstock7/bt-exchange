package org.bt.exchange.bitfinex.domain

case class Ticker(
	symbol: String,
	bid: BigDecimal,
	bidSize: BigDecimal,
	ask: BigDecimal,
	askSize: BigDecimal,
	dailyChange: BigDecimal,
	dailyChangePercent: BigDecimal,
	lastPrice: BigDecimal,
	volume: BigDecimal,
	high: BigDecimal,
	low: BigDecimal
)
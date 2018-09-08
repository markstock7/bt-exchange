package org.bt.exchange.bitfinex.domain

case class Candle(
	timestamp: BigInt,
	open: BigDecimal,
	close: BigDecimal,
	high: BigDecimal,
	low: BigDecimal,
	volume: BigDecimal
)
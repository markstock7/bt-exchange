package org.bt.exchange.binance.domain

case class TickerDaily(
	symbol: String,
	priceChange: BigDecimal,
	priceChangePercent: BigDecimal,
	weightedAvgPrice: BigDecimal,
	prevClosePrice: BigDecimal,
	lastPrice: BigDecimal,
	lastQty: BigDecimal,
	bidPrice: BigDecimal,
	askPrice: BigDecimal,
	openPrice: BigDecimal,
	highPrice: BigDecimal,
	lowPrice: BigDecimal,
	volume: BigDecimal,
	quoteVolume: BigDecimal,
	openTime: BigInt,
	closeTime: BigInt,
	firstId: BigInt,
	lastId: BigInt,
	count: BigInt
)
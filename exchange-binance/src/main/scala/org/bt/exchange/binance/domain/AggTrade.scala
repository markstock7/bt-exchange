package org.bt.exchange.binance.domain


case class AggTrade (
	symbol: String,
	price: BigDecimal,
	quantity: BigDecimal,
	timestamp: Long
)
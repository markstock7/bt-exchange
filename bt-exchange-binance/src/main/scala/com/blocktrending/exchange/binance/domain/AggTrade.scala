package com.blocktrending.exchange.binance.domain

case class AggTrade (
	symbol: String,
	price: BigDecimal,
	quantity: BigDecimal,
	timestamp: Long
)
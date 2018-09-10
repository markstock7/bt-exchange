package com.blocktrending.exchange.binance.domain

case class HistoricalTrade(
	id: Int,
	price: BigDecimal,
	qty: BigDecimal,
	time: BigInt,
	isBuyerMaker: Boolean,
	isBestMatch: Boolean
)
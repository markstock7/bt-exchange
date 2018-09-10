package com.blocktrending.exchange.binance.domain

case class TickerBook(
	symbol: String,
	bidPrice: BigDecimal,
	bidQty: BigDecimal,
	askPrice: BigDecimal,
	askQty: BigDecimal
)
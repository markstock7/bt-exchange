package com.blocktrending.exchange.binance.domain

case class TickerPrice(
	symbol: String,
	price: BigDecimal
)
package org.bt.exchange.binance.domain

case class TickerPrice(
	symbol: String,
	price: BigDecimal
)
package org.bt.exchange.binance.domain

case class PriceTicker(
	symbol: String,
	price: BigDecimal
)
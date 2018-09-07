package org.bt.exchange.binance.domain

case class OrderBookEntry(
	price: BigDecimal,
	qty:   BigDecimal
)
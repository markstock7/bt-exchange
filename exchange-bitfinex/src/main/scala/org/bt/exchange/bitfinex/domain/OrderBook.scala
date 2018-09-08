package org.bt.exchange.bitfinex.domain

case class OrderBook(
	price: BigDecimal,
	rate: BigDecimal,
	period: BigDecimal,
	count: Int,
	amount: BigDecimal
)

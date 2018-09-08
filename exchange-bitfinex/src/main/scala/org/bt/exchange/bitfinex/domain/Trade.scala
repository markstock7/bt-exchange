package org.bt.exchange.bitfinex.domain

case class Trade(
	id: BigInt,
	timestamp: BigInt,
	amount: BigDecimal,
	price: BigDecimal
)

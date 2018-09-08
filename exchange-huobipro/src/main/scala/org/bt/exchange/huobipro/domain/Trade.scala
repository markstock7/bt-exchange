package org.bt.exchange.huobipro.domain

case class Trade(
	id: BigInt,
	price: BigDecimal,
	amount: BigDecimal,
	direction: String,
	ts: BigInt
)
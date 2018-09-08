package org.bt.exchange.huobipro.domain

case class Candle(
	id: Int,
	amount: BigDecimal,
	count: BigInt,
	open: BigDecimal,
	close: BigDecimal,
	low: BigDecimal,
	high: BigDecimal,
	Vol: BigDecimal
)

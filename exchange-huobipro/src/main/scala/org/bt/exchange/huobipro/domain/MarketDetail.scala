package org.bt.exchange.huobipro.domain

case class MarketDetail(
	id: BigInt,
	ts: BigInt,
	amount: BigDecimal,
	open: BigDecimal,
	close: BigDecimal,
	high: BigDecimal,
	low: BigDecimal,
	count: BigDecimal,
	vol: BigDecimal
)
package org.bt.exchange.huobipro.domain

case class DetailMerged (
	id: BigInt,
	amount: BigDecimal,
	count: BigInt,
	open: BigDecimal,
	close: BigDecimal,
	low: BigDecimal,
	high: BigDecimal,
	vol: BigDecimal,
	ask: (BigDecimal, BigDecimal),
	bid: (BigDecimal, BigDecimal)
)
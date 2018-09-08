package org.bt.exchange.huobipro.domain

case class Ticker(
	amount: BigDecimal,
	count: BigInt,
	open: BigDecimal,
	close: BigDecimal,
	low: BigDecimal,
	high: BigDecimal,
	vol: BigDecimal,
	symbol: String
)
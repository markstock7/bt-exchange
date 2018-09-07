package org.bt.exchange.binance.domain

case class Trade(
	price: BigDecimal,
	qty: BigDecimal,
	time: Long,
	isBuyerMaker: Boolean
)
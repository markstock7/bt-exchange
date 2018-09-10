package com.blocktrending.exchange.binance.domain

case class Trade(
	price: BigDecimal,
	qty: BigDecimal,
	time: Long,
	isBuyerMaker: Boolean
)
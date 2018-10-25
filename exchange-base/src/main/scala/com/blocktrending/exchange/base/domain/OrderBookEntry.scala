package com.blocktrending.exchange.base.domain

case class OrderBookEntry(
	price: BigDecimal,
	qty:   BigDecimal
)
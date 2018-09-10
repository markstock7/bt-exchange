package com.blocktrending.exchange.binance.domain

case class OrderBookEntry(
	price: BigDecimal,
	qty:   BigDecimal
)
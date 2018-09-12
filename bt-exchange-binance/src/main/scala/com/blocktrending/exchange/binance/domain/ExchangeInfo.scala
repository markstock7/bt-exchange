package com.blocktrending.exchange.binance.domain

case class ExchangeInfo (
	timezone: String,
	serverTime: Long,
	symbols: List[ExchangeSymbol]
)
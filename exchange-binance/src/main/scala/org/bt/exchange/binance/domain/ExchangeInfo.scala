package org.bt.exchange.binance.domain

case class ExchangeInfo (
	timezone: String,
	serverTime: Long,
	symbols: Seq[ExchangeSymbol]
)
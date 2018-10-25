package com.blocktrending.exchange.binance.domain.event

/**
	* https://github.com/binance-exchange/binance-official-api-docs/blob/master/web-socket-streams.md#individual-symbol-mini-ticker-stream
	*/
case class MiniTickerEvent(
	eventType: String,
	eventTime: Long,
	symbol: String,
	close: Double,
	open: Double,
	high: Double,
	low: Double,
	baseVolume: Double,
	quoteVolume: Double
)
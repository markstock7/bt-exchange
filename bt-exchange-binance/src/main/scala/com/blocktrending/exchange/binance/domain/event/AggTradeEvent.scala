package com.blocktrending.exchange.binance.domain.event

/**
	* https://github.com/binance-exchange/binance-official-api-docs/blob/master/web-socket-streams.md#aggregate-trade-streams
	*/
case class AggTradeEvent(
	eventType: String,
	eventTime: Long,
	symbol: String,
	aggregateTrageId: Int,
	price: Double,
	quantity: Double,
	firstTradeId: Int,
	lastTradeId: Int,
	tradeTime: Long,
	isMarketMaker: Boolean,
	M: Boolean  // Ignore
)

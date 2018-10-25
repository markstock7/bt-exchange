package com.blocktrending.exchange.binance.domain.event

/**
	* Reference https://github.com/binance-exchange/binance-official-api-docs/blob/master/web-socket-streams.md#individual-symbol-ticker-streams
	*/
case class TickerEvent(
	eventType: String,
	eventTime: Long,
	symbol: String,
	priceChange: Double,
	priceChangePercent: Double,
	weightedAveragePrice: Double,
	close: Double,
	bastBidPrice: Double,
	bestBidQuantity: Double,
	bestAskPrice: Double,
	bestAskQuantity: Double,
	open: Double,
	high: Double,
	low: Double,
	baseVolume: Double,
	quoteVolume: Double,
	openTime: Long,
	closeTime: Long,
	firstTradeId: Int,
	lastTradeId: Int,
	totalNumberOfTrades: Int
)
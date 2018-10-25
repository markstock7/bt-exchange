package com.blocktrending.exchange.binance.domain.event

/**
	* https://github.com/binance-exchange/binance-official-api-docs/blob/master/web-socket-streams.md#trade-streams
	*/
case class TradeEvent(
	eventType: String,
	eventTime: Long,
	symbol: String,
	tradeId: Int,
	price: Double,
	quantity: Double,
	buyerOrderId: Int,
	sellerOrderId: Int,
	tradeTime: Long,
	isMarketMaker: Boolean,
	M: Boolean  // Ignore
)
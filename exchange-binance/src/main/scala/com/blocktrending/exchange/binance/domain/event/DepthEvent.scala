package com.blocktrending.exchange.binance.domain.event

import com.blocktrending.exchange.base.domain.OrderBookEntry


/**
	* https://github.com/binance-exchange/binance-official-api-docs/blob/master/web-socket-streams.md#diff-depth-stream
	*/
case class DepthEvent(
	eventType: String,
	eventTime: Long,
	symbol: String,
	firstUpdateId: Int,
	finalUpdateId: Int,
	bids: List[OrderBookEntry],
	asks: List[OrderBookEntry]
)


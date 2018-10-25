package com.blocktrending.exchange.binance.domain.event

import com.blocktrending.exchange.binance.domain.CandlestickInterval.CandlestickInterval

/**
	* https://github.com/binance-exchange/binance-official-api-docs/blob/master/web-socket-streams.md#klinecandlestick-streams
	*/
case class CandlestickEvent(
	eventType: String,
	eventTime: Long,
	symbol: String,
	kline: CandlestickInterval
)
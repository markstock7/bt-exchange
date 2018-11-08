package com.blocktrending.exchange.okex.domain

import com.blocktrending.exchange.base.domain.{Candle, Ticker}

case class SocketTickerResponse (
	channel: String,
	data: Seq[Ticker]
)
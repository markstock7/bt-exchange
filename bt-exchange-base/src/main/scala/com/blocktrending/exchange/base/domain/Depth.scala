package com.blocktrending.exchange.base.domain

case class Depth (
	symbol: String = "",
	bids: Seq[OrderBookEntry],
	asks: Seq[OrderBookEntry]
)

package com.blocktrending.exchange.binance.domain

case class Depth (
	symbol: String,
	bids: List[OrderBookEntry],
	asks: List[OrderBookEntry]
)

package com.blocktrending.exchange.base.domain

case class Ticker (
	symbol: String,
	close: Double,
	open: Double,
	high: Double,
	low: Double,
	baseVolume: Double,
	quoteVolume: Double,
	openTime: Long,
	closeTime: Long
)
package com.blocktrending.exchange.base.domain

case class Candle(
	symbol:                   String,
	interval:                 String,
	openTime:                 Long,
	closeTime:                Long,
	open:                     Double,
	close:                    Double,
	high:                     Double,
	low:                      Double,
	volume:                   Double,
	quoteAssetVolume:         Double,
	numberOfTrades:           Long
)
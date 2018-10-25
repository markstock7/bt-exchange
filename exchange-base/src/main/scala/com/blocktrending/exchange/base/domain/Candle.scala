package com.blocktrending.exchange.base.domain

case class Candle(
	openTime:                 Long,
	closeTime:                Long,
	open:                     BigDecimal,
	close:                    BigDecimal,
	high:                     BigDecimal,
	low:                      BigDecimal,
	volume:                   BigDecimal,
	quoteAssetVolume:         BigDecimal,
	numberOfTrades:           Long
)
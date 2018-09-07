package org.bt.exchange.binance.domain

case class ExchangeSymbol(
	symbol: String,
	status: String,
	baseAsset: String,
	baseAssetPrecision: Int,
	quoteAsset: String,
	quotePrecision: Int
)
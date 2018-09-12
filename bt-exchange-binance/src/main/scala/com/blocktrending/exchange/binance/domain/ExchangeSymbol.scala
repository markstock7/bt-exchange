package com.blocktrending.exchange.binance.domain

import com.blocktrending.exchange.binance.domain.SymbolStatus.SymbolStatus

case class ExchangeSymbol(
	symbol: String,
	status: SymbolStatus,
	baseAsset: String,
	baseAssetPrecision: Int,
	quoteAsset: String,
	quotePrecision: Int
)
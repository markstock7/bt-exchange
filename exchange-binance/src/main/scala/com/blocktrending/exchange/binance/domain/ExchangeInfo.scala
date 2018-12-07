package com.blocktrending.exchange.binance.domain

import com.blocktrending.exchange.base.domain.NestedSymbol

case class ExchangeInfo(
	symbols: Seq[NestedSymbol]
)
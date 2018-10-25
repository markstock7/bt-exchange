package com.blocktrending.exchange.binance.domain

import com.blocktrending.exchange.base.domain.NestedSymbol

case class ExchangeInfo(
	serverTime: Long,
	symbols: Seq[NestedSymbol]
)
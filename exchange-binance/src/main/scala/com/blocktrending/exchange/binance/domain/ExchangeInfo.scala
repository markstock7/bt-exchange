package com.blocktrending.exchange.binance.domain

import com.blocktrending.exchange.base.domain.NestedSymbol

case class ExchangeInfo(
	result: Seq[NestedSymbol]
)
package com.blocktrending.exchange.binance.domain.event

import com.blocktrending.exchange.binance.domain.AssetBalance

case class AccountUpdateEvent(
	eventType: String,
	eventTime: Long,
	balances:  List[AssetBalance]
)

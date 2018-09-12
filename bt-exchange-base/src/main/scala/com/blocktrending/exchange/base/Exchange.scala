package com.blocktrending.exchange.base

import io.circe.Decoder

object Exchange extends Enumeration {
	type Exchange = Value

	// @formatter:off
	val BINANCE,
			HUOBIPRO,
			OKEX,
			BITTREX,
			BITFINEX = Value
	// @formatter:on

}
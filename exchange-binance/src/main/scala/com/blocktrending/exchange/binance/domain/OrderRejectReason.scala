package com.blocktrending.exchange.binance.domain

/**
	* Order reject reason values.
	*/
object OrderRejectReason extends Enumeration {
	type OrderRejectReason = Value
	val NONE, UNKNOWN_INSTRUMENT, MARKET_CLOSED, PRICE_QTY_EXCEED_HARD_LIMITS, UNKNOWN_ORDER, DUPLICATE_ORDER, UNKNOWN_ACCOUNT, INSUFFICIENT_BALANCE, ACCOUNT_INACTIVE, ACCOUNT_CANNOT_SETTLE = Value
}
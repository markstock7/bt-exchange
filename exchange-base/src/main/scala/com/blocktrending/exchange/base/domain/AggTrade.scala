package com.blocktrending.exchange.base.domain

case class AggTrade(
	symbol: String,
	price: Double,
	quantity: Double,
	tradeTime: Long,
	isBuyerMake: Boolean
)
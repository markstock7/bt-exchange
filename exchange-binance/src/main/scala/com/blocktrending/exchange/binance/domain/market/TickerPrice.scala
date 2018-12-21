package com.blocktrending.exchange.binance.domain.market

/**
	* Wraps a symbol and its corresponding latest price.
	*/
case class TickerPrice(
	/**
		* Ticker symbol.
		*/
	symbol: String,
	/**
		* Latest price.
		*/
	price: BigDecimal
)

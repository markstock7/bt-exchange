package com.blocktrending.exchange.binance.domain.account

case class Trade(
	/**
		* Trade id.
		*/
	id: Long,
	/**
		* Price.
		*/
	price: BigDecimal,
	/**
		* Quantity.
		*/
	qty: BigDecimal,
	/**
		* Commission.
		*/
	commission: String,
	/**
		* Asset on which commission is taken
		*/
	commissionAsset: String,
	/**
		* Trade execution time.
		*/
	time:      Long,
	buyer:     Boolean,
	maker:     Boolean,
	bestMatch: Boolean,
	orderId:   Long
)

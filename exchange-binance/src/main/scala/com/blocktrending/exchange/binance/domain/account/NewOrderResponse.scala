package com.blocktrending.exchange.binance.domain.account

case class NewOrderResponse(
	/**
		* Order symbol.
		*/
	symbol: String,
	/**
		* Order id.
		*/
	orderId: Long,
	/**
		* This will be either a generated one, or the newClientOrderId parameter
		* which was passed when creating the new order.
		*/
	clientOrderId: String,
	/**
		* Transact time for this order.
		*/
	transactTime: Long
)

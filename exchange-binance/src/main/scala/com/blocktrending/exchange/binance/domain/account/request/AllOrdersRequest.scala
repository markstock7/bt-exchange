package com.blocktrending.exchange.binance.domain.account.request

case class AllOrdersRequest(
	symbol:     String,
	recvWindow: Option[Long] = None,
	timestamp:  Option[Long] = None,
	orderId:    Option[Long] = None,
	limit:      Option[Int] = None
)

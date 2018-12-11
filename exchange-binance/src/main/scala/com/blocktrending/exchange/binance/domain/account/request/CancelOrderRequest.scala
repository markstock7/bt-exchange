package com.blocktrending.exchange.binance.domain.account.request

case class CancelOrderRequest(
	symbol:            String,
	recvWindow:        Option[Long] = None,
	timestamp:         Option[Long] = None,
	orderId:           Option[Long] = None,
	origClientOrderId: Option[String] = None,
	newClientOrderId:  Option[String] = None
)

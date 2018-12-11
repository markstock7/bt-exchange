package com.blocktrending.exchange.binance.domain.account.request

case class OrderRequest(
	symbol:     String,
	recvWindow: Option[Long] = None,
	timestamp:  Option[Long] = None
)

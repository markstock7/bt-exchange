package com.blocktrending.exchange.okex.domain

case class WebSocketResponse (
	channel: String,
	binary: Int,
	data: Any
)
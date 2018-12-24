package com.blocktrending.exchange.base

import com.blocktrending.exchange.base.Exchange.Exchange
import okhttp3.{Response, WebSocket}


trait WebsocketClientHandle {

	val exchange: Exchange

	def handleClosing(webSocket: WebSocket, code: Int, reason: String): Unit = {
		println(s"$exchange Socket is Closing with reason: $reason, code: $code")
	}

	def handleOpen(webSocket: WebSocket, response: Response): Unit = {
		println(s"$exchange Socket is openging")
	}

	def handleFailure(webSocket: WebSocket, t: Throwable, response: Response): Unit = {
		println(s"$exchange Socket receive ${t.getMessage}")
	}

	def handleClosed(webSocket: WebSocket, code: Int, reason: String): Unit = {
		println(s"$exchange Socket hasClosed reason: $reason, code: $code")
	}
}
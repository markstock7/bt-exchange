package com.blocktrending.exchange.base

import com.blocktrending.util.http.ParseException
import okhttp3.{WebSocket, WebSocketListener}

trait BWebSocketListener extends WebSocketListener {
	def onParseFailure(webSocket: WebSocket, error: ParseException): Unit = println(error.toString)
}
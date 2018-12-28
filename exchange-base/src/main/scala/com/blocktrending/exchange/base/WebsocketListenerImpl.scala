package com.blocktrending.exchange.base

import com.blocktrending.util.http.ParseException
import io.circe._
import okhttp3.{WebSocket, WebSocketListener}

class WebsocketListenerImpl[T: Decoder](var callback: T => Unit) extends BWebSocketListener {
	override def onMessage(webSocket: WebSocket, text: String): Unit =
		parser.decode[T](text) match {
			case Right(event) => callback(event)
			case Left(error)  => onParseFailure(webSocket, ParseException(s"Couldn't decode $text: $error"))
		}
}
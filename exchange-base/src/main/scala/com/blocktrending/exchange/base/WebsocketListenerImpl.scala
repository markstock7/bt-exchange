package com.blocktrending.exchange.base

import com.blocktrending.util.http.{ApiExceptionCause, ApiExceptionMsg, ParseException, WebSocketConnectionClosing}
import io.circe._
import okhttp3.{Response, WebSocket, WebSocketListener}

class WebsocketListenerImpl[T: Decoder](var callback: Either[Exception, T] => Unit) extends WebSocketListener {
	override def onMessage(webSocket: WebSocket, text: String): Unit =
		parser.decode[T](text) match {
			case Right(event) => callback(Right(event))
			case Left(error)  => callback(Left(ParseException(s"Couldn't decode $text: $error")))
		}
}
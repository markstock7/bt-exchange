package com.blocktrending.exchange.binance

import java.io.IOException

import com.blocktrending.util.http.{ApiExceptionCause, ApiExceptionMsg}
import io.circe._
import okhttp3.{Response, WebSocket, WebSocketListener}

class WebsocketListenerImpl[T: Decoder](var callback: T => Unit) extends WebSocketListener {
	override def onMessage(webSocket: WebSocket, text: String): Unit =
		try {
			parser.decode[T](text) match {
				case Right(event) => callback(event)
				case Left(error)  => throw ApiExceptionMsg(s"Couldn't decode $text: $error")
			}

		} catch {
			case e: IOException =>
				throw ApiExceptionCause(e)
		}

	override def onFailure(webSocket: WebSocket, t: Throwable, response: Response): Unit =
		throw ApiExceptionCause(t)
}

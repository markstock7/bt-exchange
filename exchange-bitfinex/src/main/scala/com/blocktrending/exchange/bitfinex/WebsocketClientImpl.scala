package com.blocktrending.exchange.bitfinex

import java.io.{Closeable, IOException}

import java.nio.charset.Charset
import com.blocktrending.exchange.base.WebsocketListenerImpl
import com.blocktrending.exchange.base.domain.{AggTrade, Candle, Depth, Ticker}
import okhttp3._
import play.api.libs.json.Json
import com.blocktrending.util.StringUtil
import okio.ByteString


class WebsocketClientImpl extends Closeable {
	private val client: OkHttpClient = new OkHttpClient

	trait WebSocketCallbackImpl extends WebSocketListener {
		override def onFailure(webSocket: WebSocket, t: Throwable, response: Response): Unit = handleFailure(webSocket, t, response)
		override def onClosing(webSocket: WebSocket, code: Int, reason: String): Unit = handleClosing(webSocket, code, reason)
		override def onOpen(webSocket: WebSocket, response: Response): Unit = handleOpen(webSocket, response)
		override def onClosed(webSocket: WebSocket, code: Int, reason: String): Unit = handleClosed(webSocket, code, reason)
	}

//	def onAllTickerUpdateEvent(symbols: Seq[String])(callback: Either[Exception, Seq[Ticker]] => Unit): WebSocket =
//		createNewWebSocket(tickerChannel(symbols), new WebsocketListenerImpl(callback) with WebSocketCallbackImpl )


	private def createNewWebSocket[T](msg: String, listener: WebsocketListenerImpl[T]) = {
		client.newWebSocket(
			new Request.Builder()
				.url("wss://api.bitfinex.com/ws")
				.build,
			listener
		)
	}

	protected def handleClosing(webSocket: WebSocket, code: Int, reason: String): Unit = {}

	protected def handleOpen(webSocket: WebSocket, response: Response): Unit = {}

	protected def handleFailure(webSocket: WebSocket, t: Throwable, response: Response): Unit = {
		throw t
	}

	protected def handleClosed(webSocket: WebSocket, code: Int, reason: String): Unit = {}


	@throws[IOException]
	override def close(): Unit = client.dispatcher.executorService.shutdown()
}

//
//// request
//{
//	"event":"subscribe",
//	"channel":"book",
//	"pair":"<PAIR>",
//	"prec":"<PRECISION>",
//
//	"length":"<LENGTH>""freq":"<FREQUENCY>",
//	"length":"<LENGTH>"
//}
//
//	// response
//{
//	"event":"subscribed",
//	"channel":"book",
//	"chanId":"<CHANNEL_ID>",
//	"pair":"<PAIR>",
//	"prec":"<PRECISION>",
//	"freq":"<FREQUENCY>",
//	"len":"<LENGTH>"
//}
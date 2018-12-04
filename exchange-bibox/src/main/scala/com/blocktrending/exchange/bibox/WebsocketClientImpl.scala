package com.blocktrending.exchange.bibox

import java.io.{Closeable, IOException}

import com.blocktrending.exchange.base.{Exchange, WebsocketClientHandle, WebsocketListenerImpl}
import com.blocktrending.exchange.base.domain.{AggTrade, Candle, Depth, Ticker}
import okhttp3._
import com.blocktrending.exchange.bibox.json.SocketDecoders._
import play.api.libs.json.{JsObject, Json}

class WebsocketClientImpl extends Closeable with WebsocketClientHandle {
	private val client: OkHttpClient = new OkHttpClient
	val exchange = Exchange.BIBOX

	trait WebSocketCallbackImpl extends WebSocketListener {
		override def onFailure(webSocket: WebSocket, t: Throwable, response: Response): Unit = handleFailure(webSocket, t, response)
		override def onClosing(webSocket: WebSocket, code: Int, reason: String): Unit = handleClosing(webSocket, code, reason)
		override def onOpen(webSocket: WebSocket, response: Response): Unit = handleOpen(webSocket, response)
		override def onClosed(webSocket: WebSocket, code: Int, reason: String): Unit = handleClosed(webSocket, code, reason)
	}

	def onDepthUpdateEvent(symbol: String)(callback: Either[Exception, Depth] => Unit): WebSocket =
		createNewWebSocket(new WebsocketListenerImpl(callback) with WebSocketCallbackImpl)

  def onCandleUpdateEvent(symbol: String, interval: String)(callback: Either[Exception, Candle] => Unit): Unit =
		createNewWebSocket(new WebsocketListenerImpl(callback) with WebSocketCallbackImpl).send(Map(
			"event" -> "addChnnel",
			"channel" -> s"bibox_sub_spot_${symbol}_kline_${interval}"
		).toString())

	def onCandleUpdateEvent(symbols: Seq[String], interval: String)(callback: Either[Exception, Candle] => Unit): Unit =
    symbols.map(symbol => onCandleUpdateEvent(symbol, interval)(callback))

//    private
//  def onDealsUpdateEvent(symbol: String, interval: String)(callback: Either[Exception, Candle] => Unit): WebSocket =
//    createNewWebSocket(symbol, new WebsocketListenerImpl(callback) with WebSocketCallbackImpl )


	def createNewWebSocket[T](listener: WebsocketListenerImpl[T]) =
		client.newWebSocket(
			new Request.Builder()
				.url(s"wss://push.bibox.com/")
				.build,
			listener
		)

	@throws[IOException]
	override def close(): Unit = client.dispatcher.executorService.shutdown()
}
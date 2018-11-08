package com.blocktrending.exchange.okex

import java.io.{Closeable, IOException}
import com.blocktrending.exchange.base.WebsocketListenerImpl
import com.blocktrending.exchange.base.domain.{AggTrade, Candle, Depth, Ticker}
import okhttp3._
import play.api.libs.json.{JsValue, Json}
import com.blocktrending.exchange.okex.json.SocketDecoders._

class WebsocketClientImpl extends Closeable {
	private val client: OkHttpClient = new OkHttpClient

	trait WebSocketCallbackImpl extends WebSocketListener {
		override def onMessage(webSocket: WebSocket, text: String): Unit = {
			if (text == "{'event':'pong'}") println("Okex ping pong.")
			if (text.indexOf("addChannel") == -1) {
				Json.parse(text).as[Seq[JsValue]].foreach { text =>
					super.onMessage(webSocket, text.toString)
				}
			}
		}
		override def onFailure(webSocket: WebSocket, t: Throwable, response: Response): Unit = handleFailure(webSocket, t, response)
		override def onClosing(webSocket: WebSocket, code: Int, reason: String): Unit = handleClosing(webSocket, code, reason)
		override def onOpen(webSocket: WebSocket, response: Response): Unit = handleOpen(webSocket, response)
		override def onClosed(webSocket: WebSocket, code: Int, reason: String): Unit = handleClosed(webSocket, code, reason)
	}

	def onAllTickerUpdateEvent(symbols: Seq[String])(callback: Either[Exception, Ticker] => Unit): WebSocket =
		createNewWebSocket(tickerChannel(symbols), new WebsocketListenerImpl(callback) with WebSocketCallbackImpl )

	def onAllDepthUpdateEvent(symbols: Seq[String])(callback: Either[Exception, Depth] => Unit): WebSocket =
		createNewWebSocket(depthChannel(symbols), new WebsocketListenerImpl(callback) with WebSocketCallbackImpl )

	def onAllTradeUpdateEvent(symbols: Seq[String])(callback: Either[Exception, AggTrade] => Unit): WebSocket =
		createNewWebSocket(tradeChannel(symbols), new WebsocketListenerImpl(callback) with WebSocketCallbackImpl )

	def onAllCandleUpdateEvent(symbols: Seq[String])(callback: Either[Exception, Candle] => Unit): WebSocket =
		createNewWebSocket(candleChannel(symbols), new WebsocketListenerImpl(callback) with WebSocketCallbackImpl )


	private def createNewWebSocket[T](msg: String, listener: WebsocketListenerImpl[T]) = {
		val socket = client.newWebSocket(
			new Request.Builder()
				.url("wss://real.okcoin.com:10440/websocket/okcoinapi")
				.build,
			listener
		)
		socket send msg
		socket
	}

	protected def handleClosing(webSocket: WebSocket, code: Int, reason: String): Unit = {}

	protected def handleOpen(webSocket: WebSocket, response: Response): Unit = {}

	protected def handleFailure(webSocket: WebSocket, t: Throwable, response: Response): Unit = {}

	protected def handleClosed(webSocket: WebSocket, code: Int, reason: String): Unit = {}

	private case class ChannelCommand(
		event: String,
		channel: String
	)

	private object ChannelCommand {
		implicit val format = Json.format[ChannelCommand]
	}

	private def depthChannel(symbols: Seq[String]): String = {
		Json.stringify{
			Json.toJson(
				symbols.map { symbol =>
					ChannelCommand("addChannel", s"ok_sub_spot_${symbol}_depth")
				}
			)
		}
	}

	private def tradeChannel(symbols: Seq[String]): String = {
		Json.stringify{
			Json.toJson(
				symbols.map { symbol =>
					ChannelCommand("addChannel", s"ok_sub_spot_${symbol}_deals")
				}
			)
		}
	}

	private def tickerChannel(symbols: Seq[String]): String = {
		Json.stringify{
			Json.toJson(
				symbols.map { symbol =>
					ChannelCommand("addChannel", s"ok_sub_spot_${symbol}_ticker")
				}
			)
		}
	}

	private def candleChannel(symbols: Seq[String]): String = {
		Json.stringify{
			Json.toJson(
				symbols.map { symbol =>
					ChannelCommand("addChannel", s"ok_sub_spot_${symbol}_kline")
				}
			)
		}
	}
	@throws[IOException]
	override def close(): Unit = client.dispatcher.executorService.shutdown()
}
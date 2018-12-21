package com.blocktrending.exchange.okex

import java.io.{Closeable, IOException}
import java.util.logging.{Level, Logger}

import com.blocktrending.exchange.base.{BWebSocketListener, Exchange, WebsocketClientHandle, WebsocketListenerImpl}
import com.blocktrending.exchange.base.domain.{AggTrade, Candle, Depth, Ticker}
import com.blocktrending.exchange.okex.domain.CandlestickInterval.CandlestickInterval
import okhttp3._
import play.api.libs.json.{JsValue, Json}
import com.blocktrending.exchange.okex.json.SocketDecoders._
import okio.ByteString

class WebsocketClientImpl extends Closeable with WebsocketClientHandle {
	private val client: OkHttpClient = new OkHttpClient
	val exchange = Exchange.OKEX

	import okhttp3.OkHttpClient

	Logger.getLogger(classOf[OkHttpClient].getName).setLevel(Level.FINE)

	trait WebSocketCallbackImpl extends BWebSocketListener {
		override def onMessage(webSocket: WebSocket, bytes: ByteString): Unit = {
			val text = uncompress(bytes.toByteArray)

			if (text.indexOf("pong") != -1) {
			} else if (text.indexOf("result") == -1) {
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

	def onAllTickerUpdateEvent(symbols: Seq[String])(callback: Ticker => Unit): WebSocket =
		createNewWebSocket(tickerChannel(symbols), new WebsocketListenerImpl(callback) with WebSocketCallbackImpl )

	def onAllDepthUpdateEvent(symbols: Seq[String])(callback: Depth => Unit): WebSocket =
		createNewWebSocket(depthChannel(symbols), new WebsocketListenerImpl(callback) with WebSocketCallbackImpl )

	def onAllTradeUpdateEvent(symbols: Seq[String])(callback: AggTrade => Unit): WebSocket =
		createNewWebSocket(tradeChannel(symbols), new WebsocketListenerImpl(callback) with WebSocketCallbackImpl )

	def onAllCandleUpdateEvent(symbols: Seq[String], interval: CandlestickInterval)(callback: Candle => Unit): WebSocket =
		createNewWebSocket(candleChannel(symbols, interval), new WebsocketListenerImpl(callback) with WebSocketCallbackImpl )


	private def createNewWebSocket[T](msg: String, listener: WebsocketListenerImpl[T]) = {
		val socket = client.newWebSocket(
			new Request.Builder()
				.url("wss://real.okex.com:10441/websocket")
				.build,
			listener
		)
		socket send msg
		socket
	}

	private case class ChannelCommand(
		event: String,
		channel: String
	)

	final private object ChannelCommand {
		implicit val format = Json.format[ChannelCommand]
	}

	final private def depthChannel(symbols: Seq[String]): String = {
		Json.stringify{
			Json.toJson(
				symbols.map { symbol =>
					ChannelCommand("addChannel", s"ok_sub_spot_${SymbolTransfer.s2l(symbol)}_depth")
				}
			)
		}
	}

	final private def tradeChannel(symbols: Seq[String]): String = {
		Json.stringify{
			Json.toJson(
				symbols.map { symbol =>
					ChannelCommand("addChannel", s"ok_sub_spot_${SymbolTransfer.s2l(symbol)}_deals")
				}
			)
		}
	}

	final private def tickerChannel(symbols: Seq[String]): String = {
		Json.stringify{
			Json.toJson(
				symbols.map { symbol =>
					ChannelCommand("addChannel", s"ok_sub_spot_${SymbolTransfer.s2l(symbol)}_ticker")
				}
			)
		}
	}

	final private def candleChannel(symbols: Seq[String], interval: CandlestickInterval): String = {
		Json.stringify{
			Json.toJson(
				symbols.map { symbol =>
					ChannelCommand("addChannel", s"ok_sub_spot_${SymbolTransfer.s2l(symbol)}_kline_$interval")
				}
			)
		}
	}

	@throws[IOException]
	override def close(): Unit = client.dispatcher.executorService.shutdown()

	import java.util.zip.Inflater

	def uncompress(byteBuf: Array[Byte]): String = {
		val appender = new StringBuilder
		try {
			val infl = new Inflater(true)
			infl.setInput(byteBuf, 0, byteBuf.length)
			val result = new Array[Byte](1024)
			while ( {
				!infl.finished
			}) {
				val length = infl.inflate(result)
				appender.append(new String(result, 0, length, "UTF-8"))
			}
			infl.end()
		} catch {
			case e: Exception => e.printStackTrace()
		}
		appender.toString
	}
}
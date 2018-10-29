package com.blocktrending.exchange.binance

import java.io.{Closeable, IOException}

import com.blocktrending.exchange.base.WebsocketListenerImpl
import com.blocktrending.exchange.base.domain.{AggTrade, Candle, Depth, Ticker}
import okhttp3.{OkHttpClient, Request, Response, WebSocket}
import com.blocktrending.exchange.binance.json.SocketDecoders._

import scala.concurrent.Future

class WebsocketClientImpl extends Closeable {
	private val client = new OkHttpClient

	def errCallback: Exception => Unit = e => throw e

	def onAggTradeEventUpdate(symbol: String)(callback: AggTrade => Unit) : Unit =
		createNewWebSocket(s"$symbol@aggTrade", new WebsocketListenerImpl(callback))

	def onAggTradeEventUpdate(symbol: String, listener: WebsocketListenerImpl[AggTrade]) : Unit =
		createNewWebSocket(s"$symbol@aggTrade", listener)

	def onTradeEventUpdate(symbol: String)(callback: AggTrade => Unit): Unit =
		createNewWebSocket(s"$symbol@trade", new WebsocketListenerImpl(callback))

	def onTradeEventUpdate(symbol: String, listener: WebsocketListenerImpl[AggTrade]): Unit =
		createNewWebSocket(s"$symbol@trade", listener)


	def onAllMiniTickerUpdate(callback: Seq[Ticker] => Unit): Unit =
		createNewWebSocket(s"!miniTicker@arr", new WebsocketListenerImpl(callback))

	def onAllMiniTickerUpdate(listener: WebsocketListenerImpl[Ticker]): Unit =
		createNewWebSocket(s"!miniTicker@arr", listener)


	def onDepthUpdateEvent(symbol: String)(callback: Depth => Unit): Unit =
		createNewWebSocket(s"$symbol@depth", new WebsocketListenerImpl(callback))

	def onDepthUpdateEvent[Depth](symbol: String, listener: WebsocketListenerImpl[Depth]): Unit =
		createNewWebSocket(s"$symbol@depth", listener)


	def onTickerUpdateEvent(symbol: String)(callback: Ticker => Unit): Unit =
		createNewWebSocket(s"$symbol@ticker", new WebsocketListenerImpl(callback))

	def onTickerUpdateEvent(symbol: String, listener: WebsocketListenerImpl[Ticker]): Unit =
		createNewWebSocket(s"$symbol@ticker", listener)


	def onCandleUpdateEvent(symbols: Seq[String], interval: String)(callback: Candle => Unit): Unit =
		createNewCombainSocket(symbols.map(s => s"$s@kline_$interval"), new WebsocketListenerImpl(callback))


	def onAllTickerUpdateEvent(callback: Seq[Ticker] => Unit): Unit =
		createNewWebSocket("!ticker@arr", new WebsocketListenerImpl(callback))

	def onAllTickerUpdateEvent[Ticker](listener: WebsocketListenerImpl[Seq[Ticker]]): Unit =
		createNewWebSocket("!ticker@arr", listener)


	private def createNewWebSocket[T](channel: String, listener: WebsocketListenerImpl[T]) =
		client.newWebSocket(
			new Request.Builder()
				.url(s"wss://stream.binance.com:9443/ws/$channel")
				.build,
			listener
		)

	private def createNewCombainSocket[T](channels: Seq[String], listener: WebsocketListenerImpl[T]) =
		client.newWebSocket(
			new Request.Builder()
				.url(s"wss://stream.binance.com:9443/stream?streams=${channels.mkString("/")}")
				.build,
			listener
		)

	@throws[IOException]
	override def close(): Unit = client.dispatcher.executorService.shutdown()
}
package com.blocktrending.exchange.binance

import java.io.{Closeable, IOException}

import com.blocktrending.exchange.base.domain.{AggTrade, Candle, Depth, Ticker}
import okhttp3.{OkHttpClient, Request}
import com.blocktrending.exchange.binance.json.SocketDecoders._

class WebsocketClientImpl extends Closeable {
	private val client = new OkHttpClient

	def onAggTradeEventUpdate(symbol: String)(callback: AggTrade => Unit): Unit =
		createNewWebSocket(s"$symbol@aggTrade", new WebsocketListenerImpl(callback))

	def onTradeEventUpdate(symbol: String)(callback: AggTrade => Unit): Unit =
		createNewWebSocket(s"$symbol@trade", new WebsocketListenerImpl(callback))

	def onAllMiniTickerUpdate(callback: Seq[Ticker] => Unit): Unit =
		createNewWebSocket(s"!miniTicker@arr", new WebsocketListenerImpl(callback))

	def onDepthUpdateEvent(symbol: String)(callback: Depth => Unit): Unit =
		createNewWebSocket(s"$symbol@depth", new WebsocketListenerImpl(callback))

	def onTickerUpdateEvent(symbol: String)(callback: Ticker => Unit): Unit =
		createNewWebSocket(s"$symbol@ticker", new WebsocketListenerImpl(callback))

	def onAllTickerUpdateEvent(callback: Seq[Ticker] => Unit): Unit =
		createNewWebSocket("!ticker@arr", new WebsocketListenerImpl(callback))

	private def createNewWebSocket[T](channel: String, listener: WebsocketListenerImpl[T]) =
		client.newWebSocket(
			new Request.Builder()
				.url(s"wss://stream.binance.com:9443/ws/$channel")
				.build,
			listener
		)

	@throws[IOException]
	override def close(): Unit = client.dispatcher.executorService.shutdown()
}
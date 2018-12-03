//package com.blocktrending.exchange.bibox
//
//import java.io.{Closeable, IOException}
//
//import com.blocktrending.exchange.base.{Exchange, WebsocketClientHandle, WebsocketListenerImpl}
//import com.blocktrending.exchange.base.domain.{AggTrade, Candle, Depth, Ticker}
//import okhttp3._
//import com.blocktrending.exchange.bibox.json.SocketDecoders._
//
//class WebsocketClientImpl extends Closeable with WebsocketClientHandle {
//	private val client: OkHttpClient = new OkHttpClient
//	val exchange = Exchange.bibox
//
//	trait WebSocketCallbackImpl extends WebSocketListener {
//		override def onFailure(webSocket: WebSocket, t: Throwable, response: Response): Unit = handleFailure(webSocket, t, response)
//		override def onClosing(webSocket: WebSocket, code: Int, reason: String): Unit = handleClosing(webSocket, code, reason)
//		override def onOpen(webSocket: WebSocket, response: Response): Unit = handleOpen(webSocket, response)
//		override def onClosed(webSocket: WebSocket, code: Int, reason: String): Unit = handleClosed(webSocket, code, reason)
//	}
//
//	def onAggTradeEventUpdate(symbol: String)(callback: Either[Exception, AggTrade] => Unit): WebSocket =
//		createNewWebSocket(s"${symbol.toLowerCase}@aggTrade", new WebsocketListenerImpl(callback) with WebSocketCallbackImpl )
//
//	def onTradeEventUpdate(symbol: String)(callback: Either[Exception, AggTrade] => Unit): WebSocket =
//		createNewWebSocket(s"$symbol@trade", new WebsocketListenerImpl(callback) with WebSocketCallbackImpl )
//
//	def onTradeEventUpdate(symbols: Seq[String])(callback: Either[Exception, AggTrade] => Unit): WebSocket =
//		createNewCombainSocket(symbols.map(symbol => s"$symbol@trade"), new WebsocketListenerImpl(callback) with WebSocketCallbackImpl)
//
//
//	def onAllMiniTickerUpdate(callback: Either[Exception, Seq[Ticker]] => Unit): WebSocket =
//		createNewWebSocket(s"!miniTicker@arr", new WebsocketListenerImpl(callback) with WebSocketCallbackImpl )
//
//	def onDepthUpdateEvent(symbol: String)(callback: Either[Exception, Depth] => Unit): WebSocket =
//		createNewWebSocket(s"$symbol@depth", new WebsocketListenerImpl(callback) with WebSocketCallbackImpl )
//
//	def onDepthUpdateEvent(symbols: Seq[String])(callback: Either[Exception, Depth] => Unit): WebSocket =
//		createNewCombainSocket(symbols.map(symbol => s"$symbol@depth"), new WebsocketListenerImpl(callback) with WebSocketCallbackImpl)
//
//
//	def onTickerUpdateEvent(symbol: String)(callback: Either[Exception, Ticker] => Unit): WebSocket =
//		createNewWebSocket(s"$symbol@ticker", new WebsocketListenerImpl(callback) with WebSocketCallbackImpl)
//
//
//	def onCandleUpdateEvent(symbols: Seq[String], interval: String)(callback: Either[Exception, Candle] => Unit): WebSocket =
//		createNewCombainSocket(symbols.map(symbol => s"${symbol.toLowerCase}@kline_$interval"), new WebsocketListenerImpl(callback) with WebSocketCallbackImpl )
//
//
//	def onAllTickerUpdateEvent(callback: Either[Exception, Seq[Ticker]] => Unit): WebSocket =
//		createNewWebSocket("!ticker@arr", new WebsocketListenerImpl(callback) with WebSocketCallbackImpl )
//
//	private def createNewWebSocket[T](channel: String, listener: WebsocketListenerImpl[T]) =
//		client.newWebSocket(
//			new Request.Builder()
//				.url(s"wss://stream.bibox.com:9443/ws/$channel")
//				.build,
//			listener
//		)
//
//	private def createNewCombainSocket[T](channels: Seq[String], listener: WebsocketListenerImpl[T]) = {
//		println(channels)
//		client.newWebSocket(
//			new Request.Builder()
//				.url(s"wss://stream.bibox.com:9443/stream?streams=${channels.mkString("/")}")
//				.build,
//			listener
//		)
//	}
//
//	@throws[IOException]
//	override def close(): Unit = client.dispatcher.executorService.shutdown()
//}
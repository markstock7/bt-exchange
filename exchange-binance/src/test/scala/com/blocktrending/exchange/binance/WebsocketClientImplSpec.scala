package com.blocktrending.exchange.binance

import com.blocktrending.exchange.base.Status
import okhttp3.{Response, WebSocket}
import org.scalatest._

import scala.concurrent.{Future, Promise}

class WebsocketClientImplSpec extends AsyncFlatSpec with Matchers {

	val restClient: RestClientImpl = (new ClientFactory).newAsyncRestClient

	"onAllTickerUpdateEvent" should "Working Well" in {
		val client = new WebsocketClientImpl
		val p = Promise[Assertion]()

		Future {
			client.onAllTickerUpdateEvent {
				case Left(e) =>
					client.close()
					p.success(assert(false))
				case Right(payload) =>
					client.close()
					p.success(assert(true))
			}
		}
		p.future
	}

	"All Symbols Candle Stream" should "Working Well" in {
		val client = new WebsocketClientImpl {
			override def handleClosed(webSocket: WebSocket, code: Int, reason: String): Unit = {
				println(reason)
			}

			override def handleClosing(webSocket: WebSocket, code: Int, reason: String): Unit = {
				println(reason)
			}
		}
		restClient.symbols.flatMap { symbols =>
			val p = Promise[Assertion]()
			Future {
				client.onCandleUpdateEvent(symbols.map(_.symbol.toLowerCase), "1h") {
					case Left(e) =>
						client.close()
						p.success(assert(false))
					case Right(payload) =>
						client.close()
						p.success(assert(true))
				}
			}
			p.future
		}
	}

	"onAllCandleUpdateEvent" should "Working Well" in {
		val client = new WebsocketClientImpl {
			override def handleClosed(webSocket: WebSocket, code: Int, reason: String): Unit = {
				println(reason)
			}

			override def handleClosing(webSocket: WebSocket, code: Int, reason: String): Unit = {
				println(reason)
			}
		}
		val p = Promise[Assertion]()
		Future {
			client.onCandleUpdateEvent(Seq("btcusdt"), "1h") {
				case Left(e) =>
					client.close()
					p.success(assert(false))
				case Right(payload) =>
					client.close()
					p.success(assert(true))
			}
		}
		p.future
	}
}
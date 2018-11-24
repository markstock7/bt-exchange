package com.blocktrending.exchange.okex

import com.blocktrending.exchange.okex.domain.CandlestickInterval
import okhttp3.{Response, WebSocket}
import org.scalatest._

import scala.concurrent.{Future, Promise}

class WebsocketClientImplSepc extends AsyncFlatSpec with Matchers {

	println("OKEX 客户端测试.")


	"[OKEX] onAllTickerUpdateEvent" should "Working Well" in {
		val p = Promise[Assertion]()
		var isInit = false
		Future {
			val client = new WebsocketClientImpl
			client.onAllTickerUpdateEvent(Seq("btc_usdt")) {
				case Left(e) =>
					client.close()
					p.success(assert(false))
				case Right(ticker) =>
					client.close()
					p.success(assert(true))
			}
		}
		p.future
	}

	"[OKEX] onAllDepthUpdateEvent" should "Working Well" in {
		val p = Promise[Assertion]()
		var isInit = false
		Future {
			val client = new WebsocketClientImpl {
				override def handleFailure(webSocket: WebSocket, t: Throwable, response: Response): Unit = {
					t.printStackTrace()
					p.success(assert(false))
				}
			}
			client.onAllDepthUpdateEvent(Seq("btc_usdt")) {
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

	"[OKEX] onAllCandleUpdateEvent" should "Working Well" in {
		val p = Promise[Assertion]()
		var isInit = false
		Future {
			val client = new WebsocketClientImpl {
				override def handleFailure(webSocket: WebSocket, t: Throwable, response: Response): Unit = {
					t.printStackTrace()
					p.success(assert(false))
				}
			}
			client.onAllCandleUpdateEvent(Seq("btc_usdt"), CandlestickInterval.DAILY) {
				case Left(e) =>
					client.close()
					p.success(assert(false))
				case Right(payload) =>
					p.success(assert(true))
					client.close()

			}
		}
		p.future
	}

	"[OKEX] onAllTradeUpdateEvent" should "Working Well" in {
		val p = Promise[Assertion]()
		var isInit = false
		Future {
			val client = new WebsocketClientImpl {
				override def handleFailure(webSocket: WebSocket, t: Throwable, response: Response): Unit = {
					t.printStackTrace()
					p.success(assert(false))
				}
			}
			client.onAllTradeUpdateEvent(Seq("btc_usdt")) {
				case Left(e) =>
					client.close()
					p.success(assert(false))
				case Right(payload) =>
					p.success(assert(true))
					client.close()
			}
		}
		p.future
	}
}
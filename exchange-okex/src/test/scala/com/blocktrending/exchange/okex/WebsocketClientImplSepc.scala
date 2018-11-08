package com.blocktrending.exchange.okex

import okhttp3.{Response, WebSocket}
import org.scalatest._

import scala.concurrent.{Future, Promise}

class WebsocketClientImplSepc extends AsyncFlatSpec with Matchers {

	println("OKEX 客户端测试.")

	"[OKEX] onAllTickerUpdateEvent" should "Working Well" in {
		val p = Promise[Assertion]()
		var isInit = false
		Future {
			val client = new WebsocketClientImpl {
				override def handleFailure(webSocket: WebSocket, t: Throwable, response: Response): Unit = {
					t.printStackTrace()
					p.success(assert(false))
				}
			}
			client.onAllTickerUpdateEvent(Seq("bch_btc")) {
				case Left(e) =>
					client.close()
					p.success(assert(false))
				case Right(ticker) =>
					client.close()
					if (!isInit)
						p.success(assert(true))
						isInit = true
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
			client.onAllDepthUpdateEvent(Seq("bch_btc")) {
				case Left(e) =>
					client.close()
					p.success(assert(false))
				case Right(payload) =>
					client.close()
					if (!isInit)
						p.success(assert(true))
						isInit = true
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
			client.onAllCandleUpdateEvent(Seq("bch_btc")) {
				case Left(e) =>
					client.close()
					p.success(assert(false))
				case Right(payload) =>
					client.close()
					if (!isInit)
						p.success(assert(true))
						isInit = true

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
			client.onAllTradeUpdateEvent(Seq("btc-usdt")) {
				case Left(e) =>
					client.close()
					p.success(assert(false))
				case Right(payload) =>
					client.close()
					if (!isInit)
						p.success(assert(true))
						isInit = true
			}
		}
		p.future
	}
}
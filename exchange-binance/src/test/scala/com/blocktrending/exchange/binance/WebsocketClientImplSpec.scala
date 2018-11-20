package com.blocktrending.exchange.binance

import com.blocktrending.exchange.base.Status
import org.scalatest._

import scala.concurrent.{Future, Promise}

class WebsocketClientImplSpec extends AsyncFlatSpec with Matchers {

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

	"onAllCandleUpdateEvent" should "Working Well" in {
		val client = new WebsocketClientImpl
		val p = Promise[Assertion]()
		Future {
			client.onCandleUpdateEvent(Seq("btcusdt"), "1h") {
				case Left(e) =>
					client.close()
					p.success(assert(false))
				case Right(payload) =>
					client.close()
					println(payload)
					p.success(assert(true))
			}
		}
		p.future
	}
}
package com.blocktrending.exchange.binance

import com.blocktrending.exchange.base.Status
import org.scalatest._

class WebsocketClientImplSpec extends AsyncFlatSpec with Matchers {

	val client = (new ClientFactory).newWebSocketClient

//	"ticker24hr" should "return nonemtpy response" in {
//		restClient.ticker24hr.map { tickers =>
//			assert(tickers.nonEmpty)
//		}
//	}

//	"onAggTradeEventUpdate" should "work" in {
//		client.onAggTradeEventUpdate("BTCUSDT") { aggTrade =>
//			assert(true)
//		}
//	}
}
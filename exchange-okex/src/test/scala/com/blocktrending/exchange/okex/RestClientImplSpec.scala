package com.blocktrending.exchange.okex

import com.blocktrending.exchange.base.Status
import org.scalatest._
class RestClientImplSpec extends AsyncFlatSpec with Matchers {

	println("国内用户请翻墙后测试.")

	val restClient: RestClientImpl = (new ClientFactory).newAsyncRestClient


	"ping" should "successfully connect Binacne" in {
		restClient.ping.map { status =>
			assert(status == Status.OK)
		}
	}

	"symbols" should "return non emtpy symbols" in {
		restClient.symbols.map { symbols =>
			assert(symbols.nonEmpty)
		}
	}

	"candles" should "return non empyt candls" in {
		restClient.candles("btc-usdt", 60).map { candles =>
			assert(candles.nonEmpty)
		}
	}

}
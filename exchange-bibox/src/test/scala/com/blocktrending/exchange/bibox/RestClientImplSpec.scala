package com.blocktrending.exchange.bibox

import java.security.cert.PKIXRevocationChecker.Option

import com.blocktrending.exchange.base.Status
import com.blocktrending.exchange.bibox.domain.CandlestickInterval
import org.scalatest._
class RestClientImplSpec extends AsyncFlatSpec with Matchers {

  println("国内用户请翻墙后测试333.")

  val restClient: RestClientImpl = (new ClientFactory).newAsyncRestClient

//  "ping" should "successfully connect bibox" in {
//    restClient.ping.map { status =>
//      assert(status == Status.OK)
//    }
//  }

  it should "return non emtpy symbols" in {
    restClient.symbols.map { symbols =>
      assert(symbols.nonEmpty)
    }
  }

  "candles" should "return non empty results" in {
    restClient
      .candles("BTC_USDT", CandlestickInterval.DAILY, 1000)
      .map { candles =>
        assert(candles.nonEmpty)
      }
  }

//	"depth" should "has symbol" in {
//		restClient.depth("BTCUSDT", None).map { depth =>
//			assert(depth.symbol == "BTCUSDT")
//		}
//	}
//
//	it should "default return 100 results" in {
//		restClient.depth("BTCUSDT", None).map { depth =>
//			assert(depth.bids.length == 100)
//		}
//	}
//
//	it should "return non empty asks" in {
//		restClient.depth("BTCUSDT", None).map { depth =>
//			assert(depth.asks.nonEmpty)
//		}
//	}

//	"ticker24hr" should "return non empty results" in {
//		restClient.ticker24hr.map { tickers =>
//			assert(tickers.nonEmpty)
//		}
//	}
//
//	"trades" should "return non empty results" in {
//		restClient.trades("BTCUSDT").map { aggTrades =>
//			assert(aggTrades.nonEmpty)
//		}
//	}
//
//	"aggTrades" should "return non empty results" in {
//		restClient.aggTrades("BTCUSDT").map { aggTrades =>
//			assert(aggTrades.nonEmpty)
//		}
//	}
//

//
//	"ticker24hr" should "return" in {
//		restClient.ticker24hr("BTCUSDT").map { ticker =>
//			assert(ticker.symbol == "BTCUSDT")
//		}
//	}
//
//	it should "return all tickers" in {
//		restClient.ticker24hr.map { tickers =>
//			assert(tickers.length > 1)
//		}
//	}
//
//	"tickerPrice" should "return" in {
//		restClient.tickerPrice("BTCUSDT").map { simplePrice =>
//			assert(simplePrice.symbol == "BTCUSDT")
//		}
//	}
//
//	it should "return all simplePrices" in {
//		restClient.ticker24hr.map { simplePrices =>
//			assert(simplePrices.nonEmpty)
//		}
//	}

}

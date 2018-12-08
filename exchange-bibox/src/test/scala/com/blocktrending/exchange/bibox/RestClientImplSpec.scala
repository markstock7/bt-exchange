package com.blocktrending.exchange.bibox

import com.blocktrending.exchange.base.domain.{Depth, Ticker}
import com.blocktrending.exchange.bibox.domain.CandlestickInterval
import org.scalatest._
class RestClientImplSpec extends AsyncFlatSpec with Matchers {

  println("国内用户请翻墙后测试333.")

  val restClient: RestClientImpl = (new ClientFactory).newAsyncRestClient

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

  "tickersWithPair" should "return non empty results" in {
    restClient
      .tickersWithPair("BTC_USDT")
      .map { ticker =>
        assert(ticker.isInstanceOf[Ticker])
      }
  }

  "tickers" should "return non empty results" in {
    restClient
      .tickers
      .map { tickers =>
        assert(tickers.isInstanceOf[Seq[Ticker]])
      }
  }

  "depthWithPair" should "return non empty results" in {
    restClient
      .depthsWithPair("BTC_USDT")
      .map { depeth =>
        assert(depeth.isInstanceOf[Depth])
      }
  }

}

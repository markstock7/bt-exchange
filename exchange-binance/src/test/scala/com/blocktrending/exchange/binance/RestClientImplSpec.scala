package com.blocktrending.exchange.binance

import com.blocktrending.exchange.base.Status
import com.blocktrending.exchange.binance.domain.CandlestickInterval
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

  "candles" should "return non empty results" in {
    restClient.candles("BTCUSDT", CandlestickInterval.DAILY).map { candles =>
      assert(candles.nonEmpty)
    }
  }

  "tickersWithPair" should "return" in {
    restClient.tickersWithPair("BTCUSDT").map { ticker =>
      assert(ticker.symbol == "BTCUSDT")
    }
  }

  it should "return all tickers" in {
    restClient.tickers.map { tickers =>
      assert(tickers.length > 1)
    }
  }

  "depth" should "has symbol" in {
    restClient.depthWithPair("BTCUSDT", None).map { depth =>
      assert(depth.symbol == "BTCUSDT")
    }
  }

  "trades" should "return non empty results" in {
    restClient.tradesWithPire("BTCUSDT").map { aggTrades =>
      assert(aggTrades.nonEmpty)
    }
  }

  "aggTrades" should "return non empty results" in {
    restClient.aggTrades("BTCUSDT").map { aggTrades =>
      assert(aggTrades.nonEmpty)
    }
  }
}

package com.blocktrending.exchange.bitfinex


import com.blocktrending.exchange.base.domain.{AggTrade, Depth, Ticker}
import com.blocktrending.exchange.bitfinex.domain.CandlestickInterval
import org.scalatest._
class RestClientImplSpec extends AsyncFlatSpec with Matchers {

  println("国内用户请翻墙后测试333.")

  val restClient: RestClientImpl = (new ClientFactory).newAsyncRestClient

//  it should "return non emtpy symbols" in {
//    restClient.symbols.map { symbols =>
//      assert(symbols.nonEmpty)
//    }
//  }
//
  "candlesWithPair" should "return non empty results" in {
    restClient
      .candlesWithPair(
        "BTCUSD",
        CandlestickInterval.DAILY,
        Option(200),
        Option((System.currentTimeMillis() - 60 * 1000).toString),
        Option(System.currentTimeMillis().toString)
      ).map { candles =>
        assert(candles.nonEmpty)
      }
  }

//  "tickersWithPair" should "return non empty results" in {
//    restClient
//      .tickersWithPair("BTCUSD")
//      .map { ticker =>
//        assert(ticker.isInstanceOf[Ticker])
//      }
//  }
//
  "tickers" should "return non empty results" in {
    restClient
      .tickers
      .map { tickers =>
        assert(tickers.isInstanceOf[Seq[Ticker]])
      }
  }
//
//  "tradesWithPair" should "return non empty results" in {
//    restClient.tradesWithPair(
//      "BTCUSD",
//      Option(200),
//      Option((System.currentTimeMillis() - 60 * 1000).toString),
//      Option(System.currentTimeMillis().toString)
//    ).map {
//      trades => assert(trades.isInstanceOf[Seq[AggTrade]])
//    }
//  }
//
//  "depthWithPair" should "return non empty results" in {
//    restClient
//      .depthsWithPair("BTCUSD", "P0")
//      .map { depeth =>
//        assert(depeth.isInstanceOf[Depth])
//      }
//  }
}

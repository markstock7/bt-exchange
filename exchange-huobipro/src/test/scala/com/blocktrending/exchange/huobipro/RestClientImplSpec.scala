package com.blocktrending.exchange.huobipro

import java.security.cert.PKIXRevocationChecker.Option

import com.blocktrending.exchange.base.Status
import com.blocktrending.exchange.base.domain.{Depth, Ticker}
import com.blocktrending.exchange.huobipro.domain.CandlestickInterval
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
      .candlesWithPair("btcusdt", CandlestickInterval.DAILY, 2)
      .map { candles =>
        assert(candles.nonEmpty)
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
      .depthsWithPair("btcusdt", "step2")
      .map { depth =>
        assert(depth.isInstanceOf[Depth])
      }
  }
}

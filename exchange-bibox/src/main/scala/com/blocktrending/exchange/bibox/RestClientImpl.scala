package com.blocktrending.exchange.bibox

import com.blocktrending.exchange.base.{IAsyncRestClient, Status}
import com.blocktrending.exchange.base.Status.Status
import com.blocktrending.exchange.base.domain._
import com.blocktrending.exchange.bibox.domain.CandlestickInterval.CandlestickInterval
import com.blocktrending.exchange.bibox.domain._
import com.blocktrending.exchange.bibox.json.RestDecoders._
import com.blocktrending.util.http.RunRequest

import scala.concurrent.{ExecutionContext, Future}

class RestClientImpl(service: RestApiService)(implicit ex: ExecutionContext)
    extends IAsyncRestClient {

  override def symbols: Future[Seq[NestedSymbol]] =
    RunRequest.apply1[PairResponse](
      service.pairList
    ).map(_.result)

  def candles(pair: String, period: CandlestickInterval, size: Int): Future[Seq[Candle]] =
    RunRequest.apply1[CandleRepsonse](
      service.candlesWithPair(pair, "30min", size)
    ).map(_.result).map(candles => candles.map(_.copy(symbol = pair, interval = period.toString)))

  // tickers
  def tickersWithPair(pair: String): Future[Ticker] =
    RunRequest.apply1[TickerResponse](
      service.tickersWithPair(pair)
    ).map(_.result)

  def tickers: Future[Seq[Ticker]] =
    RunRequest.apply1[TickersResponse](
      service.tickers
    ).map(_.result)

  // depth
  def depthsWithPair(pair: String): Future[Depth] =
    RunRequest.apply1[DepthResponse](
      service.depthsWithPair(pair, 200)
    ).map(_.result)
}

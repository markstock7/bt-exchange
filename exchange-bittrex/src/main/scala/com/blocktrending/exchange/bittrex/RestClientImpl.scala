package com.blocktrending.exchange.bittrex

import com.blocktrending.exchange.base.{IAsyncRestClient}
import com.blocktrending.exchange.base.domain._
import com.blocktrending.exchange.bittrex.domain.CandlestickInterval.CandlestickInterval
import com.blocktrending.exchange.bittrex.domain._
import com.blocktrending.exchange.bittrex.json.RestDecoders._
import com.blocktrending.util.http.RunRequest

import scala.concurrent.{ExecutionContext, Future}

class RestClientImpl(service: RestApiService)(implicit ex: ExecutionContext)
    extends IAsyncRestClient {

  // symbols
  override def symbols: Future[Seq[NestedSymbol]] =
    RunRequest.apply1[PairResponse](
      service.pairList
    ).map(_.result)

  // candles
  def depthsWithPair(pair: String, period: CandlestickInterval, time: String): Future[Seq[Candle]] =
    RunRequest.apply1[CandleResponse](
      service.candlesWithPair(pair, period.toString, time)
    ).map(_.result).map(candles => candles.map(candle =>
      candle.copy(symbol = pair, interval = period.toString, openTime = candle.openTime + CandlestickInterval.interval2Period(period))
    ))

  // tickers
  def tickersWithPair(pair: String): Future[Ticker] =
    RunRequest.apply1[TickersResponse](
      service.tickersWithPair(pair)
    ).map(_.result.head)

  def tickers: Future[Seq[Ticker]] =
    RunRequest.apply1[TickersResponse](
      service.tickers
    ).map(_.result)

  // Trade history
  // TODO 没有找到官方公布的内容

  // depth
  def depthsWithPair(pair: String): Future[Depth] =
    RunRequest.apply1[Depth](
      service.depthsWithPair(pair)
    )

  // def depths: Future[Seq[Depth]] =
  // TODO 接口不支持
}
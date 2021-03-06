package com.blocktrending.exchange.okex

import com.blocktrending.exchange.base.{IAsyncRestClient}
import com.blocktrending.exchange.base.domain._
import com.blocktrending.exchange.okex.domain.CandlestickInterval.CandlestickInterval
import com.blocktrending.exchange.okex.domain._
import com.blocktrending.exchange.okex.json.RestDecoders._
import com.blocktrending.util.http.RunRequest

import scala.concurrent.{ExecutionContext, Future}

class RestClientImpl(service: RestApiService)(implicit ex: ExecutionContext)
    extends IAsyncRestClient {
  // symbols

  // candles
  // candlesWithPair

  // Tickers
  // TickersWithPair

  // trades
  // tradesWithPair

  // Depths
  // DepthsWithPair

  // symbols
  override def symbols: Future[Seq[NestedSymbol]] =
    RunRequest.apply1[Seq[NestedSymbol]](
      service.pairList
  )

  // candles
  def candlesWithPair(pair: String, period: CandlestickInterval, start: String, end: String): Future[Seq[Candle]] =
    RunRequest.apply1[Seq[Candle]](
      service.candlesWithPair(pair, period.toString, start, end)
    ).map(candles => candles.map(candle =>
      candle.copy(symbol = pair, interval = period.toString, closeTime = candle.openTime + CandlestickInterval.interval2Period(period))
    ))

  // tickers
  def tickersWithPair(pair: String): Future[Ticker] =
    RunRequest.apply1[Ticker](
      service.tickersWithPair(pair)
    )

  def tickers: Future[Seq[Ticker]] =
    RunRequest.apply1[Seq[Ticker]](
      service.tickers
    )

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
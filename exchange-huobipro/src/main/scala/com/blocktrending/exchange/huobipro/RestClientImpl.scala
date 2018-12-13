package com.blocktrending.exchange.huobipro

import com.blocktrending.exchange.base.{IAsyncRestClient}
import com.blocktrending.exchange.base.domain._
import com.blocktrending.exchange.huobipro.domain.CandlestickInterval.CandlestickInterval
import com.blocktrending.exchange.huobipro.domain._
import com.blocktrending.exchange.huobipro.json.RestDecoders._
import com.blocktrending.util.http.RunRequest

import scala.concurrent.{ExecutionContext, Future}

// symbols

// candles
// candlesWithPair

// Tickers
// TickersWithPair

// trades
// tradesWithPair

// Depths
// DepthsWithPair

class RestClientImpl(service: RestApiService)(implicit ex: ExecutionContext)
    extends IAsyncRestClient {

  // symbols
  override def symbols: Future[Seq[NestedSymbol]] =
    RunRequest.apply1[PairResponse](
      service.pairList
    ).map(_.data)

  // candles
  def candlesWithPair(pair: String, period: CandlestickInterval, size: Int): Future[Seq[Candle]] =
    RunRequest.apply1[CandleResponse](
      service.candlesWithPair(pair, period.toString, size)
    ).map(_.data).map(candles => candles.map(candle =>
      candle.copy(symbol = pair, interval = period.toString, closeTime = candle.openTime + CandlestickInterval.interval2Period(period))
    ))

  // tickers, 有tickers 就暂时不提供 ticker
  //  def tickersWithPair(pair: String): Future[Ticker] =
  //    RunRequest.apply1[TickerResponse](
  //      service.tickersWithPair(pair)
  //    ).map(_.data)

  def tickers: Future[Seq[Ticker]] =
    RunRequest.apply1[TickersResponse](
      service.tickers
    ).map(_.data)

  // Trade history
  // TODO 官方不支持按照时间点去拿成交详细

  // depth
  def depthsWithPair(pair: String, aggtype: String): Future[Depth] =
    RunRequest.apply1[DepthResponse](
      service.depthsWithPair(pair, aggtype)
    ).map(_.data)

  // def depths: Future[Seq[Depth]] =
  // TODO 接口不支持
}
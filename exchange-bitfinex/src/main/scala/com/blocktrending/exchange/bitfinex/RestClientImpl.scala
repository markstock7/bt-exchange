package com.blocktrending.exchange.bitfinex

import com.blocktrending.exchange.base.domain._
import com.blocktrending.exchange.bitfinex.domain.CandlestickInterval.CandlestickInterval
import com.blocktrending.util.http.RunRequest
import com.blocktrending.exchange.bitfinex.json.RestDecoders._
import scala.concurrent.{ExecutionContext, Future}
class RestClientImpl(service: RestApiService)(implicit ex: ExecutionContext) {

  // symbols

  // candles
  // candlesWithPair

  // Tickers
  // TickersWithPair

  // trades
  // tradesWithPair

  // Depths
  // DepthsWithPair

  def symbols: Future[Seq[NestedSymbol]] = RunRequest.apply1[Seq[NestedSymbol]](
    service.symbols
  )

  // 没有 一次性返回全部内容的接口，支持时间粒度会拿数据
  def candlesWithPair(
      symbol: String,
      interval: CandlestickInterval,
      limit: Option[Int] = None,
      startTime: Option[String] = None,
      endTime: Option[String] = None
  ): Future[Seq[Candle]] = RunRequest.apply1[Seq[Candle]](
    service.candlesWithPair(interval.toString,
                    symbol,
                    limit.toString,
                    startTime.toString,
                    endTime.toString)
  )

  def tickers: Future[Seq[Ticker]] = RunRequest.apply1[Seq[Ticker]](
    service.tickers
  )

  def tickersWithPair(pair: String): Future[Ticker] = RunRequest.apply1[Ticker](
    service.tickersWithPair(pair)
  )

  // def trades
  // TODO 没有接口支持

  // 支持时间会拿接口数据
  def tradesWithPair(
      symbol: String,
      limit: Option[Int] = None,
      startTime: Option[String] = None,
      endTime: Option[String] = None
  ): Future[Seq[AggTrade]] = RunRequest.apply1[Seq[AggTrade]](
    service.tradesWithPair(symbol,
                           limit.toString,
                           startTime.toString,
                           endTime.toString)
  )

  // def depths
  // TODO 没有接口支持

  def depthsWithPair(
      symbol: String,
      precision: String
  ): Future[Depth] = RunRequest.apply1[Seq[OrderBookEntry]](
    service.depthsWithPair(symbol, precision)
  ).map {
    (list) => {
      val asks: Seq[OrderBookEntry] = list.filter(_.qty > 0)
      val bids: Seq[OrderBookEntry] = list.filter(_.qty < 0)
      Depth(symbol, asks, bids)
    }
  }
}

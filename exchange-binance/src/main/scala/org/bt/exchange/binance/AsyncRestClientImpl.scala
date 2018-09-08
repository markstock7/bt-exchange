package org.bt.exchange.binance

import org.bt.exchange.base.{AsJava, RunRequest}
import org.bt.exchange.binance.domain.CandlestickInterval.CandlestickInterval
import org.bt.exchange.binance.domain._
import json.RestDecoders._

import scala.concurrent.{ExecutionContext, Future}

class AsyncRestClientImpl(service: RestApiService)(implicit ex: ExecutionContext) {

	def ping: Future[Unit] = RunRequest[Unit](service.ping)

	def time: Future[ServerTime] = RunRequest[ServerTime](service.time)

	def exchangeInfo: Future[ExchangeInfo] = RunRequest[ExchangeInfo](service.exchangeInfo)

	def depth(
		symbol: String,
		limit: Option[Int]
	): Future[Depth] = RunRequest[Depth](
		service.depth(
			AsJava(symbol),
			AsJava(limit)
		)
	)

	def trades(
		symbol:   String,
		limit:    Option[Int]
	): Future[List[Trade]] = RunRequest[List[Trade]](
		service.trades(
			AsJava(symbol),
			AsJava(limit)
		)
	)

	def historicalTrades(
		symbol:   String,
		limit:    Option[Int],
		formId:   Option[Long]
	): Future[List[HistoricalTrade]] = RunRequest[List[HistoricalTrade]](
		service.historicalTrades(
			AsJava(symbol),
			AsJava(limit),
			AsJava(formId)
		)
	)

	def aggTrades(
		symbol:    String,
		fromId:    Option[Long] = None,
		limit:     Option[Int] = None,
		startTime: Option[Long] = None,
		endTime:   Option[Long] = None
	): Future[List[AggTrade]] = RunRequest[List[AggTrade]](
		service.aggTrades(
			AsJava(symbol),
			AsJava(fromId),
			AsJava(limit),
			AsJava(startTime),
			AsJava(endTime)
		)
	)

	def candles(
		symbol:     String,
		interval:   CandlestickInterval,
		limit:      Option[Int] = None,
		startTime:  Option[Long] = None,
		endTime:    Option[Long] = None
	): Future[List[Candle]] = RunRequest[List[Candle]](
		service.candles(
			AsJava(symbol),
			AsJava(interval),
			AsJava(limit),
			AsJava(startTime),
			AsJava(endTime)
		)
	)

	def ticker24hr(
		symbol:     Option[String]
	): Future[TickerDaily] = RunRequest[TickerDaily](
		service.ticker24hr(
			AsJava(symbol)
		)
	)

	def tickerPrice(
		symbol:     Option[String]
	): Future[TickerPrice] = RunRequest[TickerPrice](
		service.tickerPrice(
			AsJava(symbol)
		)
	)

	def bookTicker(
		symbol:     Option[String]
	): Future[TickerBook] = RunRequest[TickerBook](
		service.bookTicker(
			AsJava(symbol)
		)
	)

	def symbols: Future[Seq[Int]] = {
		RunRequest[ExchangeInfo](service.exchangeInfo).map { exchangeInfo =>
			exchangeInfo.symbols.map { symbol =>
				symbol
				0
			}
		}
	}

}
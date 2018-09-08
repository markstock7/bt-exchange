package org.bt.exchange.bitfinex

import org.bt.exchange.base.{AsJava, RunRequest}
import org.bt.exchange.bitfinex.domain._
import org.bt.exchange.bitfinex.domain.CandlestickInterval.CandlestickInterval
import org.bt.exchange.bitfinex.domain.Section.Section

import scala.concurrent.{ExecutionContext, Future}

class AsyncRestClientImpl(service: RestApiService)(implicit ex: ExecutionContext) {

	def status: Future[Unit] = RunRequest[Unit](service.status)

	def tickers: Future[List[Ticker]] = ???
	def ticker: Future[Ticker] = ???
	def trades: Future[List[Trade]] = ???
	def books: Future[List[OrderBook]] = ???
	def calcTradeAvg: Future[List[Stat]] = ???


	def candles(
		timeFrame: CandlestickInterval,
		symbol: String,
		section: Section,
		limit: Int,
		start: BigInt,
		end: BigInt,
		sort: Int,
	): Future[List[Candle]] = RunRequest[List[Candle]](
		service.candles(
			AsJava(timeFrame.toString),
			AsJava(symbol),
			AsJava(section.toString),
			AsJava(limit),
			AsJava(start),
			AsJava(end),
			AsJava(sort)
		)
	)
}
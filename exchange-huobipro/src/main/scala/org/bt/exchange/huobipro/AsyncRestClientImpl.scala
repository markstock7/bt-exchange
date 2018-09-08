package org.bt.exchange.huobipro


import org.bt.exchange.base.{AsJava, RunRequest}
import org.bt.exchange.huobipro.domain._
import org.bt.exchange.huobipro.domain.CandlestickInterval.CandlestickInterval

import scala.concurrent.{ExecutionContext, Future}

class AsyncRestClientImpl(service: RestApiService)(implicit ex: ExecutionContext) {

	def candle(
		symbol:     String,
		interval:   CandlestickInterval,
		size: Option[Int]
	): Future[List[Candle]] = RunRequest[List[Candle]](
		service.candles(
			AsJava(symbol),
			AsJava(interval),
			AsJava(size)
		)
	)

	def deailMerged(
		symbol: String
	): Future[DetailMerged] = RunRequest[DetailMerged](
		service.detailMerged(
			AsJava(symbol)
		)
	)

	def tickers: Future[List[Ticker]] = RunRequest[List[Ticker]](
		service.tickers
	)

	def depth(
		symbol: String,
		`type`: String
	): Future[Depth] = RunRequest[Depth](
		service.depth(
			AsJava(symbol),
			AsJava(`type`)
		)
	)

	def trade(
		symbol: String
	): Future[Trade] = RunRequest[Trade](
		service.trade(
			AsJava(symbol)
		)
	)

	def historyTrade(
		symbol: String,
		size: Int
	): Future[List[Trade]] = RunRequest[List[Trade]](
		service.historyTrade(
			AsJava(symbol),
			AsJava(size)
		)
	)

	def marketDetail(
		symbol: String
	): Future[MarketDetail] = RunRequest[MarketDetail](
		service.marketDetail(
			AsJava(symbol)
		)
	)

	def symbols: Future[List[Symbol]] = RunRequest[List[Symbol]](
		service.symbols
	)

	def timestamp: Future[BigInt] = RunRequest[BigInt](
		service.timestamp
	)
}
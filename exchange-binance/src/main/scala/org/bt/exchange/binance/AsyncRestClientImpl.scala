package org.bt.exchange.binance

import org.bt.exchange.base.{AsJava, RunRequest}
import org.bt.exchange.binance.domain.CandlestickInterval.CandlestickInterval
import org.bt.exchange.binance.domain.{AggTrade, Candle, Depth, ExchangeInfo}
import json.RestDecoders._

import scala.concurrent.{ExecutionContext, Future}

class AsyncRestClientImpl(service: RestApiService)(implicit ex: ExecutionContext) {

	def ping: Future[Unit] = RunRequest[Unit](service.ping)

	def candlestickBars(
		symbol:     String,
		interval:   CandlestickInterval,
		limit:      Option[Int] = None,
		startTime:  Option[Long] = None,
		endTime:    Option[Long] = None
	): Future[List[Candle]] = RunRequest[List[Candle]](
		service.getCandlestickBars(
			AsJava(symbol),
			AsJava(interval),
			AsJava(limit),
			AsJava(startTime),
			AsJava(endTime)
		)
	)

	def getAggTrades(
		symbol:    String,
		fromId:    Option[String] = None,
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

	def getDepth(symbol: String, limit: Int): Future[Depth] = RunRequest[Depth](
		service.getDepth(
			AsJava(symbol),
			AsJava(limit)
		)
	)

	def symbols: Future[Seq[Int]] = {
		RunRequest[ExchangeInfo](service.getExchangeInfo).map { exchangeInfo =>
			exchangeInfo.symbols.map { symbol =>
				0
//				AssetPair(symbol.baseAsset, symbol.quoteAsset)
			}
		}
	}

}
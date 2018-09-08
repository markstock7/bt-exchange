package org.bt.exchange.bitfinex

import org.bt.exchange.base.{AsJava, RunRequest}
import org.bt.exchange.bitfienx.domain.Candle
import org.bt.exchange.bitfienx.domain.Section.Section
import org.bt.exchange.bitfienx.domain.TimeFrame.TimeFrame

import scala.concurrent.{ExecutionContext, Future}

class AsyncRestClientImpl(service: RestApiService)(implicit ex: ExecutionContext) {

	def ping: Future[Unit] = RunRequest[Unit](service.status)

	def candles(
		timeFrame: TimeFrame,
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
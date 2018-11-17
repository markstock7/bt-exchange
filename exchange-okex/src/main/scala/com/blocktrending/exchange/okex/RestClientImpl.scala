package com.blocktrending.exchange.okex

import com.blocktrending.exchange.base.{IAsyncRestClient, Status}
import com.blocktrending.exchange.base.Status.Status
import com.blocktrending.exchange.base.domain._
import com.blocktrending.util.AsJava
import com.blocktrending.util.http.RunRequest
import com.blocktrending.exchange.okex.json.RestDecoders._

import scala.concurrent.{ExecutionContext, Future}

class RestClientImpl(service: RestApiService)(implicit ex: ExecutionContext) extends IAsyncRestClient {

	def ping: Future[Status] = symbols.map(_ => Status.OK).recover { case e => Status.NON_RESPONSE }

	def symbols: Future[Seq[NestedSymbol]] = RunRequest.apply1[Seq[NestedSymbol]](
		service.instrument
	)

	def candles(symbol: String, granularity: Int, start: Option[String] = None, end: Option[String] = None): Future[Seq[Candle]] =
		RunRequest.apply1[Seq[Candle]](
			service.candles(
				AsJava(SymbolTransfer.s2l(symbol)),
				AsJava(granularity),
				AsJava(start),
				AsJava(end)
			)
		).map { candles =>
			candles.map(c => c.copy(closeTime = c.closeTime + granularity * 1000 - 1))
		}
}
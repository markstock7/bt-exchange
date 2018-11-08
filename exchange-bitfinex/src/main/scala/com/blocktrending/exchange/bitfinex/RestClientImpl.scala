package com.blocktrending.exchange.bitfinex

import com.blocktrending.exchange.base.{IAsyncRestClient, Status}
import com.blocktrending.exchange.base.Status.Status
import com.blocktrending.exchange.base.domain._
import com.blocktrending.util.AsJava
import com.blocktrending.util.http.RunRequest
import com.blocktrending.exchange.bitfinex.json.RestDecoders._

import scala.concurrent.{ExecutionContext, Future}

class RestClientImpl(service: RestApiService)(implicit ex: ExecutionContext) {

	def ping: Future[Status] = symbols.map(_ => Status.OK).recover{ case e => Status.NON_RESPONSE }

	def symbols: Future[Seq[String]] = RunRequest.apply1[Seq[String]](
		service.symbols
	)
}
package com.blocktrending.exchange.base

import com.blocktrending.exchange.base.Status.Status
import com.blocktrending.exchange.base.domain.NestedSymbol

import scala.concurrent.Future

trait IAsyncRestClient {
//	def ping: Future[Status]
	def symbols: Future[Seq[NestedSymbol]]
}
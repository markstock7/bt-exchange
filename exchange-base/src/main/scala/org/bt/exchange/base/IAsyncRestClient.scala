package org.bt.exchange.base


import scala.concurrent.Future

case class Symbol()

trait IAsyncRestClient {

	def ping: Future[Unit]


}
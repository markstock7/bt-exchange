package com.blocktrending.exchange.binance

import retrofit2.Retrofit

import scala.concurrent.ExecutionContext

class ClientFactory(implicit ex: ExecutionContext) {
	final private lazy val service: RestApiService = {
		new Retrofit.Builder().baseUrl("https://api.binance.com").build.create(classOf[RestApiService])
	}

	def newAsyncRestClient = new RestClientImpl(service)

	def newWebSocketClient = new WebsocketClientImpl
}
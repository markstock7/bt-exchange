package com.blocktrending.exchange.bibox

import retrofit2.Retrofit

import scala.concurrent.ExecutionContext

class ClientFactory(implicit ex: ExecutionContext) {
	final private lazy val service: RestApiService = {
		new Retrofit.Builder().baseUrl("https://api.bibox.com").build.create(classOf[RestApiService])
	}

	def newAsyncRestClient = new RestClientImpl(service)

	def newWebSocketClient = new WebsocketClientImpl
}
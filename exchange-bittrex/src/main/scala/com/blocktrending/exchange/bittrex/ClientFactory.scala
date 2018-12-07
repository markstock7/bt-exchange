package com.blocktrending.exchange.bittrex

import retrofit2.Retrofit

import scala.concurrent.ExecutionContext

class ClientFactory(implicit ex: ExecutionContext) {
	final private lazy val service1: RestApiService1 = {
		new Retrofit.Builder().baseUrl("https://bittrex.com/api/v1.1/").build.create(classOf[RestApiService1])
	}

	final private lazy val service2: RestApiService2 = {
		new Retrofit.Builder().baseUrl("https://bittrex.com/Api/v2.0/pub/").build.create(classOf[RestApiService2])
	}

	def newAsyncRestClient = new RestClientImpl(service1, service2)
}
package com.blocktrending.exchange.binance

import okhttp3.OkHttpClient
import retrofit2.Retrofit

import scala.concurrent.ExecutionContext

class ClientFactory(val apiKey: String, val secret: String)(implicit ex: ExecutionContext) {
	final private lazy val service: RestApiService = {
		val httpClient = new OkHttpClient.Builder
		val builder    = new Retrofit.Builder().baseUrl("https://api.binance.com")
		var retrofit   = builder.build

		if (apiKey.nonEmpty && secret.nonEmpty) {
			val interceptor = new AuthenticationInterceptor(apiKey, secret)

			if (!httpClient.interceptors.contains(interceptor)) {
				httpClient.addInterceptor(interceptor)
				builder.client(httpClient.build)
				retrofit = builder.build
			}
		}

		retrofit.create(classOf[RestApiService])
	}

	def newAsyncRestClient = new RestClientImpl(service)

	def newWebSocketClient = new WebsocketClientImpl
}

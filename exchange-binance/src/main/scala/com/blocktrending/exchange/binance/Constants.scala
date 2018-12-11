package com.blocktrending.exchange.binance


object Constants {
			/**
				* REST API base URL.
				*/
			final val API_BASE_URL = "https://api.binance.com"

			/**
				* Streaming API base URL.
				*/
			final val WS_API_BASE_URL = "wss://stream.binance.com:9443/ws"

			/**
				* HTTP Header to be used for API-KEY authentication.
				*/
			final val API_KEY_HEADER = "X-MBX-APIKEY"

			/**
				* Decorator to indicate that an endpoint requires an API key.
				*/
			final val ENDPOINT_SECURITY_TYPE_APIKEY = "APIKEY"
			final val ENDPOINT_SECURITY_TYPE_APIKEY_HEADER: String = ENDPOINT_SECURITY_TYPE_APIKEY + ": #"

			/**
				* Decorator to indicate that an endpoint requires a signature.
				*/
			final val ENDPOINT_SECURITY_TYPE_SIGNED = "SIGNED"
			final val ENDPOINT_SECURITY_TYPE_SIGNED_HEADER: String = ENDPOINT_SECURITY_TYPE_SIGNED + ": #"

			/**
				* Default receiving window.
				*/
			final val DEFAULT_RECEIVING_WINDOW: Long = 6000000L

}


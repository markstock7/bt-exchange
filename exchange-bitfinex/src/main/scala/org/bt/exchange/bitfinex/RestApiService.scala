package org.bt.exchange.bitfinex


import java.math.BigInteger

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.{GET, Path, Query, _}

trait RestApiService {

	@GET("/v2/platform/status")
	def status: Call[ResponseBody]

	@GET("/v2/tickers")
	def tickers(
		@Query("symbols") symbols: String
	): Call[ResponseBody]

	@GET("/v2/ticker/{ticker}")
	def ticker(
		@Path("ticker") ticker: String
	): Call[ResponseBody]


	@GET("/v2/trades/{ticker}/hist")
	def trades(
		@Path("ticker") ticker: String,
		@Query("limit") limit: Integer,
		@Query("start") start: java.lang.Long,
		@Query("end")   end:   java.lang.Long,
		@Query("sort")  sort:  Integer
	): Call[ResponseBody]

	@GET("/v2/book/{ticker}/P0")
	def books(
		@Path("ticker") ticker: String
	): Call[ResponseBody]

	@GET("/candles/trade{TimeFrame}:{Symbol}/{Section}")
	def candles(
		@Path("TimeFrame") timeFrame: String,
		@Path("Symbol") symbol: String,
		@Path("Section") section: String,
		@Query("limit") limit: Integer,
		@Query("start") start: java.lang.Long,
		@Query("end")   end:   java.lang.Long,
		@Query("sort")  sort:  Integer
	): Call[ResponseBody]
}
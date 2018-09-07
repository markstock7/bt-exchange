package org.bt.exchange.binance

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http._

trait RestApiService {

	@GET("/api/v1/ping")
	def ping: Call[ResponseBody]

	@GET("/api/v1/depth")
	def getOrderBook(
		@Query("symbol") symbol: String,
		@Query("limit") limit:   Integer
	): Call[ResponseBody]

	@GET("/api/v1/klines")
	def getCandlestickBars(
		@Query("symbol") symbol:       String,
		@Query("interval") interval:   String,
		@Query("limit") limit:         Integer,
		@Query("startTime") startTime: java.lang.Long,
		@Query("endTime") endTime:     java.lang.Long
	): Call[ResponseBody]

	@GET("/api/v1/aggTrades")
	def aggTrades(
		@Query("symbol") symbol:       String,
		@Query("fromId") fromId:       String,
		@Query("limit") limit:         Integer,
		@Query("startTime") startTime: java.lang.Long,
		@Query("endTime") endTime:     java.lang.Long
	): Call[ResponseBody]

	@GET("/api/v1/ticker/allPrices")
	def getLatestPrices: Call[ResponseBody]

	@GET("/api/v1/ticker/24hr")
	def get24HrPriceStatistics(
		@Query("symbol") symbol: String
	): Call[ResponseBody]

	@GET("/api/v1/depth")
	def getDepth(symbol: String, limit: Int): Call[ResponseBody]

	@GET("/api/v1/exchangeInfo")
	def getExchangeInfo: Call[ResponseBody]
}
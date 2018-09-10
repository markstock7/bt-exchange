package com.blocktrending.exchange.binance

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http._

trait RestApiService {

	@GET("/api/v1/ping")
	def ping: Call[ResponseBody]

	@GET("api/v1/time")
	def time: Call[ResponseBody]

	@GET("/api/v1/exchangeInfo")
	def exchangeInfo: Call[ResponseBody]

	@GET("/api/v1/depth")
	def depth(
		@Query("symbol")          symbol:       String,
		@Query("limit")           limit :       Integer
	): Call[ResponseBody]

	@GET("/api/v1/trades")
	def trades(
		@Query("symbol")          symbol:       String,
		@Query("limit")           limit :       Integer
	): Call[ResponseBody]

	@GET("/api/v1/historicalTrades")
	def historicalTrades(
		@Query("symbol")          symbol:       String,
		@Query("limit")           limit :       Integer,
		@Query("fromId")          fromId:       java.lang.Long
	): Call[ResponseBody]

	@GET("/api/v1/aggTrades")
	def aggTrades(
		@Query("symbol")          symbol:       String,
		@Query("fromId")          fromId:       java.lang.Long,
		@Query("limit")           limit:        Integer,
		@Query("startTime")       startTime:    java.lang.Long,
		@Query("endTime")         endTime:      java.lang.Long
	): Call[ResponseBody]


	@GET("/api/v1/klines")
	def candles(
		@Query("symbol")          symbol:       String,
		@Query("interval")        interval:     String,
		@Query("limit")           limit:        Integer,
		@Query("startTime")       startTime:    java.lang.Long,
		@Query("endTime")         endTime:      java.lang.Long
	): Call[ResponseBody]

	@GET("/api/v1/ticker/24hr")
	def ticker24hr(
		@Query("symbol")          symbol:       String
	): Call[ResponseBody]

	@GET("/api/v3/ticker/price")
	def tickerPrice(
		@Query("symbol")          symbol:       String
	): Call[ResponseBody]

	@GET("/api/v3/ticker/bookTicker")
	def bookTicker(
		@Query("symbol")          symbol:       String
	): Call[ResponseBody]

}
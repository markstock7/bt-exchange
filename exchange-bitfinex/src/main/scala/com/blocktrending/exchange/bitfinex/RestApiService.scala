package com.blocktrending.exchange.bitfinex

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http._

trait RestApiService {

	@GET("/symbols")
	def symbols: Call[ResponseBody]

	@GET("/api/spot/v3/instruments/<instrument_id>/candles")
	def candles(
		@Path("instrument_id")   instrument_id: java.lang.String,
		@Query("start")          start:         java.lang.String,
		@Query("end")            end:           java.lang.String,
		@Query("granularity")    granularity:   Integer
	): Call[ResponseBody]

	@GET("/v2/candles/trade:TimeFrame:Symbol/Section")
	def candles(
		@Path("TimeFrame")  TimeFrame: java.lang.String,
		@Path("Symbol")     Symbol   : java.lang.String,
		@Path("Section")    Section  : java.lang.String,
		@Query("limit")     limit    : java.lang.Integer,
		@Query("start")     start    : java.lang.String,
		@Query("end")       end      : java.lang.String
	): Call[ResponseBody]
}
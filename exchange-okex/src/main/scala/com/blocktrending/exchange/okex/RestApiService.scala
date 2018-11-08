package com.blocktrending.exchange.okex

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http._

trait RestApiService {

	@GET("/api/spot/v3/instruments")
	def instrument: Call[ResponseBody]

	@GET("/api/spot/v3/instruments/{instrument_id}/candles")
	def candles(
		@Path("instrument_id")   instrument_id: java.lang.String,
		@Query("granularity")    granularity:   Integer,
		@Query("start")          start:         java.lang.String,
		@Query("end")            end:           java.lang.String,
	): Call[ResponseBody]
}
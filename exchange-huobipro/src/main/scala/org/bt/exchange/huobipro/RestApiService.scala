package org.bt.exchange.huobipro

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http._

trait RestApiService {

	@GET("/market/history/kline")
	def candles(
		@Query("symbol")  symbol:   String,
		@Query("period")  period:   String,
		@Query("size")    size:     Int
	): Call[ResponseBody]

	@GET("/market/detail/merged")
	def detailMerged(
		@Query("symbol") symbol: String
	): Call[ResponseBody]

	@GET("/market/tickers")
	def tickers: Call[ResponseBody]

	@GET("/market/depth")
	def depth(
		@Query("symbol")  symbol: String,
		@Query("type")    `type`: String
	): Call[ResponseBody]

	@GET("/market/trade")
	def trade(
		@Query("symbol")  symbol: String
	): Call[ResponseBody]

	@GET("/market/history/trade")
	def historyTrade(
		@Query("symbol") symbol: String,
		@Query("size") size: Integer
	): Call[ResponseBody]

	@GET("/market/detail")
	def marketDetail(
		@Query("symbol") symbol: String
	): Call[ResponseBody]

	@GET("/v1/common/symbols")
	def symbols: Call[ResponseBody]

	@GET("/v1/common/timestamp")
	def timestamp: Call[ResponseBody]

}
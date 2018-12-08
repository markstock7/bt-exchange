package com.blocktrending.exchange.bitfinex

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http._

trait RestApiService {
  // symbols

  // candles
  // candlesWithPair

  // Tickers
  // TickersWithPair

  // trades
  // tradesWithPair

  // Depths
  // DepthsWithPair

  @GET("/v1/symbols")
  def symbols: Call[ResponseBody]

  // 注意，这里的symbol 要传 tBTCUSD, 前面加个小的前缀
  // 官方没有说limit的最大取值范围
  @GET("/v2/candles/trade:{TimeFrame}:t{Symbol}/hist")
  def candlesWithPair(
      @Path("TimeFrame") interval: java.lang.String,
      @Path("Symbol") symbol: java.lang.String,
      @Query("limit") limit: java.lang.String,
      @Query("start") start: java.lang.String,
      @Query("end") end: java.lang.String
  ): Call[ResponseBody]

  @GET("/v2/tickers?symbols=ALL")
  def tickers: Call[ResponseBody]

  @GET("/v2/ticker/t{symbol}")
  def tickersWithPair(
      @Path("symbol") symbol: java.lang.String,
  ): Call[ResponseBody]

  @GET("/v2/trades/t{symbol}/hist")
  def tradesWithPair(
      @Path("symbol") symbol: java.lang.String,
      @Query("limit") limit: java.lang.String,
      @Query("start") start: java.lang.String,
      @Query("end") end: java.lang.String
  ): Call[ResponseBody]

  // Precision: Level of price aggregation (P0, P1, P2, P3, P4, R0)
  @GET("/v2/book/t{Symbol{/{Precision}")
  def depthsWithPair(
      @Path("Symbol") Symbol: java.lang.String,
      @Path("Precision") Precision: java.lang.String,
  ): Call[ResponseBody]
}

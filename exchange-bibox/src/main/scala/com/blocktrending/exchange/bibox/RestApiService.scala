package com.blocktrending.exchange.bibox

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http._

trait RestApiService {

  /*
  * {
    "result":[
        {
            "id":1,// 交易对id
            "pair":"BIX_BTC" //交易对symbol
        },
        {
            "id":2,
            "pair":"ETH_BTC"
        }
    ],
    "cmd":"api/pairList"
  }
  * */
  @GET("v1/mdata?cmd=pairList")
  def pairList: Call[ResponseBody]

	// k线也只支持最近的1000条数据，不能支持更老的K线数据，历史K线获取不了
  /*{
    "result":[
    {
      "time":1512660420000, // 时间戳
      "open":"0.00586568",  // 开盘价
      "high":"0.00586568",  // 最高价
      "low":"0.00586568",   // 最低价
      "close":"0.00586568", // 收盘价
      "vol":"0"             // 成交量
    },
    {
      "time":1512660480000,
      "open":"0.00586568",
      "high":"0.00586568",
      "low":"0.00586568",
      "close":"0.00586568",
      "vol":"10"
    }
    ],
    "cmd":"api/kline"
  }*/
  @GET("/v1/mdata?cmd=kline")
  def candlesWithPair(
      @Query("pair") pair: String, // 系统支持的交易对 格式为： BIX_BTC
      @Query("period") period: String, // '1min', '3min', '5min', '15min', '30min', '1hour', '2hour', '4hour', '6hour', '12hour', 'day', 'week'
      @Query("size") size: Integer // 1-1000
  ): Call[ResponseBody]

  @GET("/v1/mdata?cmd=depth")
  def depthsWithPair(
      @Query("pair")   pair: String,
      @Query("size")   size: Integer // 1-200
  ): Call[ResponseBody]

	// 只能获取最新200条，不能指定起始时间或者order ID, 也就是说，拿不到历史的成交记录
	@GET("/v1/mdata?cmd=deals")
  def tradesWithPair(
      @Query("pair")   pair: String,
      @Query("size")   size: Integer // 1-200
  ): Call[ResponseBody]

	@GET("/v1/mdata?cmd=marketAll")
	def tickers: Call[ResponseBody]

	@GET("/v1/mdata?cmd=market")
	def tickersWithPair(
      @Query("pair")   pair: String,
  ): Call[ResponseBody]
}

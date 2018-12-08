package com.blocktrending.exchange.bittrex

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http._

trait RestApiService {

  /**
    * {
    * "success" : true,
    * "message" : "",
    * "result" : [{
    * "MarketCurrency" : "LTC",
    * "BaseCurrency" : "BTC",
    * "MarketCurrencyLong" : "Litecoin",
    * "BaseCurrencyLong" : "Bitcoin",
    * "MinTradeSize" : 0.01000000,
    * "MarketName" : "BTC-LTC",
    * "IsActive" : true,
    * "Created" : "2014-02-13T00:00:00"
    * }
    * ]}
    **/
  @GET("/api/v1.1/public/getmarkets")
  def pairList: Call[ResponseBody]

  /** {
    * "success" : true,
    * "message" : "",
    * "result" : [{
    * "MarketName" : "BTC-888",
    * "High" : 0.00000919,
    * "Low" : 0.00000820,
    * "Volume" : 74339.61396015,
    * "Last" : 0.00000820,
    * "BaseVolume" : 0.64966963,
    * "TimeStamp" : "2014-07-09T07:19:30.15",
    * "Bid" : 0.00000820,
    * "Ask" : 0.00000831,
    * "OpenBuyOrders" : 15,
    * "OpenSellOrders" : 15,
    * "PrevDay" : 0.00000821,
    * "Created" : "2014-03-20T06:00:00",
    * "DisplayMarketName" : null
    * }
    * ]
    * } **/
  @GET("/api/v1.1/public/getmarketsummaries")
  def tickers: Call[ResponseBody]

  /**
    * {
    * "success" : true,
    * "message" : "",
    * "result" : [{
    * "MarketName" : "BTC-888",
    * "High" : 0.00000919,
    * "Low" : 0.00000820,
    * "Volume" : 74339.61396015,
    * "Last" : 0.00000820,
    * "BaseVolume" : 0.64966963,
    * "TimeStamp" : "2014-07-09T07:19:30.15",
    * "Bid" : 0.00000820,
    * "Ask" : 0.00000831,
    * "OpenBuyOrders" : 15,
    * "OpenSellOrders" : 15,
    * "PrevDay" : 0.00000821,
    * "Created" : "2014-03-20T06:00:00",
    * "DisplayMarketName" : null
    * }
    **/
  @GET("/api/v1.1/public/getmarketsummaries")
  def tickersWithPair(
    // 系统支持的交易对 格式为： BTC-CVC
    @Query("marketName") marketName: String,
    ): Call[ResponseBody]

  /** {
	"success" : true,
	"message" : "",
	"result" : {
		"buy" : [{
				"Quantity" : 12.37000000,
				"Rate" : 0.02525000
			}
		],
		"sell" : [{
				"Quantity" : 32.55412402,
				"Rate" : 0.02540000
			}, {
				"Quantity" : 84.00000000,
				"Rate" : 0.02600000
			}
		]
	}**/
  @GET("/api/v1.1/public/getorderbook?type=both")
  def depthsWithPair(
    // 系统支持的交易对 格式为： BTC-CVC
    @Query("market") market: String,
    ): Call[ResponseBody]

	// 官方也没有限制条数，时间戳也只是可能，看了 bittrex 官方也是用的这个接口，没有加时间戳，一次性取回了全部数据
  /**{
    success : true,
    message : "",
    result : [ // Array of candle objects.
    {
      BV: 13.14752793,          // base volume
      C: 0.000121,              // close
      H: 0.00182154,            // high
      L: 0.0001009,             // low
      O: 0.00182154,            // open
      T: "2017-07-16T23:00:00", // timestamp
      V: 68949.3719684          // 24h volume
    }]
  }**/
  @GET("/Api/v2.0/pub/market/GetTicks")
  def candlesWithPair(
      // 系统支持的交易对 格式为： BTC-CVC
      @Query("marketName") marketName: String,
      // must be in [“oneMin”, “fiveMin”, “thirtyMin”, “hour”, “day”]
      @Query("tickInterval") tickInterval: String,
      @Query("_") time: String // 时间戳
  ): Call[ResponseBody]
}

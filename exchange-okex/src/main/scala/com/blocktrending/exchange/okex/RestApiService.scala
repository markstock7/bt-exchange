package com.blocktrending.exchange.okex

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http._

trait RestApiService {

  /**
    * {
[{
"instrument_id": "BTC-USDT",

"base_currency": "BTC",

"quote_currency": "USDT",

"min_size": "0.001",

"size_increment": "0.00000001",

"tick_size": "0.0001"
}]
    * ]}
    **/
  @GET("/api/spot/v3/instruments")
  def pairList: Call[ResponseBody]

  // 官方也没有限制条数，时间戳也只是可能，看了 bittrex 官方也是用的这个接口，没有加时间戳，一次性取回了全部数据
  /**{
    success : true,
    message : "",
    result : [ // Array of candle objects.
{
"close":7071.1913,
"high":7072.7999,
"low":7061.7,
"open":7067.9008,
"time":"2018-08-05T10:00:00.000Z",
"volume":68.4532745
},
]
  }**/
  @GET("/api/spot/v3/instruments/{symbol}/candles")
  def candlesWithPair(
      // 系统支持的交易对 格式为： LTC-USDT
      @Path("symbol") symbol: String,
      // must be in [60 180 300 900 1800 3600 7200 14400 21600 43200 86400 604800]
      // 这些值分别对应的是[1min 3min 5min 15min 30min 1hour 2hour 4hour 6hour 12hour 1day 1week]的时间段
      @Query("granularity") granularity: String,
      @Query("start") start: String, // 时间戳
      @Query("end") end: String
  ): Call[ResponseBody]

  /** {
    * "success" : true,
    * "message" : "",
    * "result" : [{
"instrument_id": "BTC-USDT",

"last": "333.99",

"best_bid": "333.98",

"best_ask": "333.99",
"open_24h": "0.193",
"high_24h": "0.193",

"low_24h": "333.98",

"base_volume_24h": "5957.11914015",

"quote_volume_24h": "5957.11914015",

"timestamp": "2015-11-14T20:46:03.511Z"

    * }
    * ]
    * } **/
  @GET("/api/spot/v3/instruments/ticker")
  def tickers: Call[ResponseBody]

  /**
    * {
    * "success" : true,
    * "message" : "",
    * "result" : [{
“instrument_id": "BTC-USDT",

"last": "333.99",

"best_bid": "333.98",

"best_ask": "333.99",

"high_24h": "0.193",

"open_24h": "0.193",

"low_24h": "333.98",

"base_volume_24h": "5957.11914015",

"quote_volume_24h": "5957.11914015",

"timestamp": "2015-11-14T20:46:03.511Z"
    * }
    **/
  @GET("/api/spot/v3/instruments/{symbol}/ticker")
  def tickersWithPair(
      // 系统支持的交易对 格式为： LTC-USDT
      @Path("symbol") symbol: String,
  ): Call[ResponseBody]

  /** {
{
  "timestamp": "2016-12-08T20:09:05.508Z",
  "bids": [
    [ price, size, num_orders ],
    [ "295.96", "4.39088265", 2 ],
  ],
  "asks": [
    [ price, size, num_orders ],
    [ "295.97", "25.23542881", 12 ],
  ]
}
  }**/
  @GET("/api/spot/v3/instruments/{symbol}/book")
  def depthsWithPair(
      // 系统支持的交易对 格式为： LTC-USDT
      @Path("symbol") symbol: String,
  ): Call[ResponseBody]
}

package com.blocktrending.exchange.huobipro

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http._

// symbols

// candles
// candlesWithPair

// Tickers
// TickersWithPair

// trades
// tradesWithPair

// Depths
// DepthsWithPair

trait RestApiService {

  /**
    * {
/* GET /v1/common/symbols */
{
  "status": "ok",
  "data": [
    {
        "base-currency": "btc",
        "quote-currency": "usdt",
        "price-precision": 2,
        "amount-precision": 4,
        "symbol-partition": "main",
        "symbol": "btcusdt"
    }
    {
        "base-currency": "eth",
        "quote-currency": "usdt",
        "price-precision": 2,
        "amount-precision": 4,
        "symbol-partition": "main",
        "symbol": "ethusdt"
    }
  ]
}
    **/
  @GET("/v1/common/symbols")
  def pairList: Call[ResponseBody]

  // 官方也没有限制条数，时间戳也只是可能
  /**{
    success : true,
    message : "",
    data : [ // Array of candle objects.
    {
    "id": K线id（时间戳）,
    "amount": 成交量,
    "count": 成交笔数,
    "open": 开盘价,
    "close": 收盘价,当K线为最晚的一根时，是最新成交价
    "low": 最低价,
    "high": 最高价,
    "vol": 成交额, 即 sum(每一笔成交价 * 该笔的成交量)
    }]
  }**/
  @GET("/market/history/kline")
  // 妈的，不支持时间或者ID 回去数据
  def candlesWithPair(
      // 系统支持的交易对 格式为： btcusdt
      @Query("symbol") symbol: String,
      // must be in [1min, 5min, 15min, 30min, 60min, 1day, 1mon, 1week, 1year]
      @Query("period") period: String,
      @Query("size") size: Int // [1 - 2000]
  ): Call[ResponseBody]

  /** {
    {
    "status":"ok",
    "ts":1510885463001,
    "data":[
        {
            "open":0.044297,      // 日K线 开盘价
            "close":0.042178,     // 日K线 收盘价
            "low":0.040110,       // 日K线 最低价
            "high":0.045255,      // 日K线 最高价
            "amount":12880.8510,  // 24小时成交量
            "count":12838,        // 24小时成交笔数
            "vol":563.0388715740, // 24小时成交额
            "symbol":"ethbtc"     // 交易对
        }
    ]
    * } **/
  @GET("/market/tickers")
  def tickers: Call[ResponseBody]

  /**
    * {
  "id":1499225271,
  "ts":1499225271000,
  "close":1885.0000,
  "open":1960.0000,
  "high":1985.0000,
  "low":1856.0000,
  "amount":81486.2926,
  "count":42122,
  "vol":157052744.85708200,
  "ask":[1885.0000,21.8804],
  "bid":[1884.0000,1.6702]
    * }
    **/
  @GET("/market/detail/merged")
  def tickersWithPair(
      // 系统支持的交易对 格式为： ethusdt
      @Query("symbol") symbol: String,
  ): Call[ResponseBody]

  /** {
/* GET /market/depth?symbol=ethusdt&type=step1 */
   "status": "ok",
  "ch": "market.btcusdt.depth.step1",
  "ts": 1489472598812,
  "tick": {
    "id": 1489464585407,
    "ts": 1489464585407,
    "bids": [
      [7964, 0.0678], // [price, amount]
      [7963, 0.9162],
      [7961, 0.1],
      [7960, 12.8898],
      [7958, 1.2],
      [7955, 2.1009],
      [7954, 0.4708],
	}**/
  @GET("/market/depth")
  def depthsWithPair(
      // 系统支持的交易对 格式为： btcusdt
      @Query("symbol") symbol: String,
      @Query("type") aggtype: String // step0, step1, step2, step3, step4, step5（合并深度0-5）；step0时，不合并深度
  ): Call[ResponseBody]

}

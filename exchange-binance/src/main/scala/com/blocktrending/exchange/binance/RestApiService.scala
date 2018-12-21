package com.blocktrending.exchange.binance

import com.blocktrending.exchange.binance.domain.OrderSide.OrderSide
import com.blocktrending.exchange.binance.domain.OrderType.OrderType
import com.blocktrending.exchange.binance.domain.TimeInForce.TimeInForce
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http._

trait RestApiService {

	@GET("/api/v1/ping")
	def ping: Call[ResponseBody]

	@GET("/api/v1/ticker/allPrices")
	def getLatestPrices: Call[ResponseBody]

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

	@Headers(Array("SIGNED: #"))
	@POST("/api/v3/order") def newOrder(
		@Query("symbol") symbol:           String,
		@Query("side") side:               String,
		@Query("type") `type`:             String,
		@Query("timeInForce") timeInForce: String,
		@Query("quantity") quantity:       String,
		@Query("price") price:             String,
		@Query("stopPrice") stopPrice:     String,
		@Query("icebergQty") icebergQty:   String,
		@Query("recvWindow") recvWindow:   java.lang.Long,
		@Query("timestamp") timestamp:     java.lang.Long,
		@Query("newOrderRespType") newOrderRespType: String = "RESULT"): Call[ResponseBody]


	@Headers(Array("SIGNED: #"))
	@GET("/api/v3/order") def orderStatus(
		@Query("symbol") symbol:                       String,
		@Query("orderId") orderId:                     java.lang.Long,
		@Query("origClientOrderId") origClientOrderId: String,
		@Query("recvWindow") recvWindow:               java.lang.Long,
		@Query("timestamp") timestamp:                 java.lang.Long): Call[ResponseBody]

	@Headers(Array("SIGNED: #"))
	@DELETE("/api/v3/order") def cancelOrder(
		@Query("symbol") symbol:                       String,
		@Query("orderId") orderId:                     java.lang.Long,
		@Query("origClientOrderId") origClientOrderId: String,
		@Query("newClientOrderId") newClientOrderId:   String,
		@Query("recvWindow") recvWindow:               java.lang.Long,
		@Query("timestamp") timestamp:                 java.lang.Long): Call[ResponseBody]

	@Headers(Array("SIGNED: #"))
	@GET("/api/v3/openOrders") def openOrders(
		@Query("symbol") symbol:         String,
		@Query("recvWindow") recvWindow: java.lang.Long,
		@Query("timestamp") timestamp:   java.lang.Long): Call[ResponseBody]

	@Headers(Array("SIGNED: #"))
	@GET("/api/v3/allOrders") def allOrders(
		@Query("symbol") symbol:         String,
		@Query("orderId") orderId:       java.lang.Long,
		@Query("limit") limit:           Integer,
		@Query("recvWindow") recvWindow: java.lang.Long,
		@Query("timestamp") timestamp:   java.lang.Long): Call[ResponseBody]

	@Headers(Array("SIGNED: #"))
	@GET("/api/v3/myTrades") def getMyTrades(
		@Query("symbol") symbol:         String,
		@Query("limit") limit:           Integer,
		@Query("fromId") fromId:         java.lang.Long,
		@Query("recvWindow") recvWindow: java.lang.Long,
		@Query("timestamp") timestamp:   java.lang.Long): Call[ResponseBody]

	@Headers(Array("SIGNED: #"))
	@POST("/wapi/v3/withdraw.html") def withdraw(
		@Query("asset") asset:           String,
		@Query("address") address:       String,
		@Query("amount") amount:         String,
		@Query("name") name:             String,
		@Query("recvWindow") recvWindow: java.lang.Long,
		@Query("timestamp") timestamp:   java.lang.Long): Call[ResponseBody]

	@Headers(Array("SIGNED: #"))
	@GET("/wapi/v3/depositHistory.html") def depositHistory(
		@Query("asset") asset:           String,
		@Query("recvWindow") recvWindow: java.lang.Long,
		@Query("timestamp") timestamp:   java.lang.Long
	): Call[ResponseBody]

	@Headers(Array("SIGNED: #"))
	@GET("/wapi/v3/withdrawHistory.html") def withdrawHistory(
		@Query("asset") asset:           String,
		@Query("recvWindow") recvWindow: java.lang.Long,
		@Query("timestamp") timestamp:   java.lang.Long
	): Call[ResponseBody]

	@Headers(Array("SIGNED: #"))
	@GET("/wapi/v3/depositAddress.html") def depositAddress(
		@Query("asset") asset:           String,
		@Query("recvWindow") recvWindow: java.lang.Long,
		@Query("timestamp") timestamp:   java.lang.Long
	): Call[ResponseBody]

	@Headers(Array("SIGNED: #"))
	@GET("/api/v3/account") def account(
		@Query("recvWindow") recvWindow: java.lang.Long,
		@Query("timestamp") timestamp:   java.lang.Long
	): Call[ResponseBody]

	@Headers(Array("APIKEY: #"))
	@POST("/api/v1/userDataStream") def startUserDataStream: Call[ResponseBody]

	@Headers(Array("APIKEY: #"))
	@PUT("/api/v1/userDataStream") def keepAliveUserDataStream(@Query("listenKey") listenKey: String): Call[ResponseBody]

	@Headers(Array("APIKEY: #"))
	@DELETE("/api/v1/userDataStream") def closeAliveUserDataStream(
		@Query("listenKey") listenKey: String
	): Call[ResponseBody]
}
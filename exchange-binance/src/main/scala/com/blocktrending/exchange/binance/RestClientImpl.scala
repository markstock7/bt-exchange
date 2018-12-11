package com.blocktrending.exchange.binance

import java.lang.System.currentTimeMillis
import com.blocktrending.exchange.base.{IAsyncRestClient, Status}
import com.blocktrending.exchange.base.Status.Status
import com.blocktrending.exchange.base.domain._
import com.blocktrending.exchange.binance.Constants.DEFAULT_RECEIVING_WINDOW
import com.blocktrending.exchange.binance.domain.CandlestickInterval.CandlestickInterval
import com.blocktrending.exchange.binance.domain.account.{NewOrder, Order}
import com.blocktrending.exchange.binance.domain.event.ListenKey
import com.blocktrending.exchange.binance.domain.{ExchangeInfo, ServerResponse}
import com.blocktrending.exchange.binance.json.RestDecoders._
import com.blocktrending.util.AsJava
import com.blocktrending.util.http.RunRequest
import godzilla.exchange.domain.account.request.{AllOrdersRequest, CancelOrderRequest, OrderRequest, OrderStatusRequest}
import io.circe.Decoder

import scala.concurrent.{ExecutionContext, Future}

class RestClientImpl(service: RestApiService)(implicit ex: ExecutionContext) extends IAsyncRestClient {

	def ping: Future[Status] = RunRequest.apply1[ServerResponse](
		service.time
	).map(_ => Status.OK).recover{
		case _ => Status.NON_RESPONSE
	}

	override def symbols: Future[Seq[NestedSymbol]] = RunRequest.apply1[ExchangeInfo](
		service.exchangeInfo
	).map(_.symbols)

	def depth(
		symbol: String,
		limit: Option[Int]
	): Future[Depth] = RunRequest.apply1[Depth](
		service.depth(AsJava(symbol), AsJava(limit))
	).map(_.copy(symbol = symbol))

	object Trades {
		implicit lazy val AggTradeDecoder: Decoder[AggTrade] = Decoder.forProduct4(
			"price",
			"qty",
			"time",
			"isBuyerMaker"
		)((price: Double, qty: Double, time: Long, isBuyerMaker: Boolean) => AggTrade("", price, qty, time, isBuyerMaker))

		def trades(
			symbol: String,
			limit: Option[Int] = None
		): Future[Seq[AggTrade]] = RunRequest.apply1[Seq[AggTrade]](
			service.trades(AsJava(symbol), AsJava(limit))
		)
	}

	def trades(
		symbol: String,
		limit: Option[Int] = None
	): Future[Seq[AggTrade]] = Trades.trades(symbol, limit)


	object AggTrades {
		implicit lazy val AggTradeDecoder: Decoder[AggTrade] = Decoder.forProduct4(
			"p",
			"q",
			"T",
			"m"
		)((price: Double, qty: Double, time: Long, isBuyerMaker: Boolean) => AggTrade("", price, qty, time, isBuyerMaker))

		def aggTrades(
			symbol: String,
			fromId: Option[Long] = None,
			limit: Option[Int] = None,
			startTime: Option[Long] = None,
			endTime: Option[Long] = None
		): Future[Seq[AggTrade]] = RunRequest.apply1[Seq[AggTrade]](
			service.aggTrades(AsJava(symbol), AsJava(fromId), AsJava(limit), AsJava(startTime), AsJava(endTime))
		)
	}

	def aggTrades(
		symbol: String,
		fromId: Option[Long] = None,
		limit: Option[Int] = None,
		startTime: Option[Long] = None,
		endTime: Option[Long] = None
	): Future[Seq[AggTrade]] = AggTrades.aggTrades(symbol, fromId, limit, startTime, endTime)

	def candles(
		symbol:     String,
		interval:   CandlestickInterval,
		limit:      Option[Int] = None,
		startTime:  Option[Long] = None,
		endTime:    Option[Long] = None
	): Future[Seq[Candle]] = RunRequest.apply1[Seq[Candle]](
		service.candles(AsJava(symbol), AsJava(interval), AsJava(limit), AsJava(startTime), AsJava(endTime))
	).map(candles => candles.map(_.copy(symbol = symbol, interval = interval.toString)))

	def ticker24hr(
		symbol:      String
	): Future[Ticker] = RunRequest.apply1[Ticker](
		service.ticker24hr(AsJava(symbol))
	)

	def ticker24hr: Future[Seq[Ticker]] = RunRequest.apply1[Seq[Ticker]] {
		val symbol: Option[String] = None
		service.ticker24hr(AsJava(symbol))
	}

	def tickerPrice(
		symbol:     String
	): Future[SimpleTicker] = RunRequest.apply1[SimpleTicker](
		service.tickerPrice(AsJava(symbol))
	)

	def tickerPrice: Future[Seq[SimpleTicker]] = RunRequest.apply1[Seq[SimpleTicker]] {
		val symbol: Option[String] = None
		service.tickerPrice(AsJava(symbol))
	}


	override def newOrder(order: NewOrder): Future[Order] = {
		RunRequest[Order](
			service.newOrder(
				AsJava(order.symbol),
				AsJava(order.side),
				AsJava(order.`type`),
				AsJava(order.timeInForce),
				AsJava(order.quantity),
				AsJava(order.price),
				AsJava(order.stopPrice),
				AsJava(order.icebergQty),
				AsJava(order.recvWindow),
				AsJava(currentTimeMillis)
			)
		)
	}

	override def getOrderStatus(in: OrderStatusRequest): Future[Order] =
		RunRequest[Order](
			service.orderStatus(
				AsJava(in.symbol),
				AsJava(in.orderId),
				AsJava(in.origClientOrderId),
				AsJava.or(in.recvWindow, DEFAULT_RECEIVING_WINDOW),
				AsJava.or(in.timestamp, currentTimeMillis)
			)
		)

	override def cancelOrder(in: CancelOrderRequest): Future[Unit] =
		RunRequest[Unit](
			service.cancelOrder(
				AsJava(in.symbol),
				AsJava(in.orderId),
				AsJava(in.origClientOrderId),
				AsJava(in.newClientOrderId),
				AsJava.or(in.recvWindow, DEFAULT_RECEIVING_WINDOW),
				AsJava.or(in.timestamp, currentTimeMillis)
			)
		)

	override def getOpenOrders(in: OrderRequest): Future[List[Order]] =
		RunRequest[List[Order]](
			service.openOrders(AsJava(in.symbol),
				AsJava.or(in.recvWindow, DEFAULT_RECEIVING_WINDOW),
				AsJava.or(in.timestamp, currentTimeMillis))
		)

	override def getAllOrders(in: AllOrdersRequest): Future[List[Order]] =
		RunRequest[List[Order]](
			service.allOrders(
				AsJava(in.symbol),
				AsJava(in.orderId),
				AsJava(in.limit),
				AsJava.or(in.recvWindow, DEFAULT_RECEIVING_WINDOW),
				AsJava.or(in.timestamp, currentTimeMillis)
			)
		)

	def startUserDataStream: Future[ListenKey] =
			RunRequest[ListenKey](service.startUserDataStream)

	def keepAliveUserDataStream(listenKey: ListenKey): Future[Unit] =
			RunRequest[Unit](service.keepAliveUserDataStream(listenKey.listenKey))

	def closeUserDataStream(listenKey: ListenKey): Future[Unit] =
			RunRequest[Unit](service.closeAliveUserDataStream(listenKey.listenKey))

}
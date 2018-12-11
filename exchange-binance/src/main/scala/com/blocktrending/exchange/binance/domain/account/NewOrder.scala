package com.blocktrending.exchange.binance.domain.account

import com.blocktrending.exchange.binance.domain.OrderSide.OrderSide
import com.blocktrending.exchange.binance.domain.OrderType.OrderType
import com.blocktrending.exchange.binance.domain.TimeInForce.TimeInForce
import com.blocktrending.exchange.binance.domain.{OrderSide, OrderType}


/**
	* A trade order to enter or exit a position.
	*/
object NewOrder {

	/**
		* Places a MARKET buy order for the given <code>quantity</code>.
		*
		* @return a new order which is pre-configured with MARKET as the order type and BUY as the order side.
		*/
	def marketBuy(symbol: String, quantity: BigDecimal) =
		new NewOrder(symbol, OrderSide.BUY, OrderType.MARKET, None, quantity)

	/**
		* Places a MARKET sell order for the given <code>quantity</code>.
		*
		* @return a new order which is pre-configured with MARKET as the order type and SELL as the order side.
		*/
	def marketSell(symbol: String, quantity: BigDecimal) =
		new NewOrder(symbol, OrderSide.SELL, OrderType.MARKET, None, quantity)

	/**
		* Places a LIMIT buy order for the given <code>quantity</code> and <code>price</code>.
		*
		* @return a new order which is pre-configured with LIMIT as the order type and BUY as the order side.
		*/
	def limitBuy(symbol: String, timeInForce: TimeInForce, quantity: BigDecimal, price: BigDecimal) =
		new NewOrder(symbol, OrderSide.BUY, OrderType.LIMIT, Some(timeInForce), quantity, Some(price))

	/**
		* Places a LIMIT sell order for the given <code>quantity</code> and <code>price</code>.
		*
		* @return a new order which is pre-configured with LIMIT as the order type and SELL as the order side.
		*/
	def limitSell(symbol: String, timeInForce: TimeInForce, quantity: BigDecimal, price: BigDecimal) =
		new NewOrder(symbol, OrderSide.SELL, OrderType.LIMIT, Some(timeInForce), quantity, Some(price))
}

case class NewOrder(
	/**
		* Symbol to place the order on.
		*/
	symbol: String,
	/**
		* Buy/Sell order side.
		*/
	side: OrderSide,
	/**
		* Type of order.
		*/
	`type`: OrderType,

	timeInForce: Option[TimeInForce],

	/**
		* Quantity.
		*/
	quantity: BigDecimal,
	/**
		* Price.
		*/
	price: Option[BigDecimal] = None,
	/**
		* A unique id for the order. Automatically generated if not sent.
		*/
	newClientOrderId: Option[String] = None,
	/**
		* Used with stop orders.
		*/
	stopPrice: Option[BigDecimal] = None,
	/**
		* Used with iceberg orders.
		*/
	icebergQty: Option[BigDecimal] = None,
	/**
		* Receiving window.
		*/
	recvWindow: Option[Long] = None,
	/**
		* Order timestamp.
		*/
	timestamp: Option[Long] = None
)
